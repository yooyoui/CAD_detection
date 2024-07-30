# 代码介绍

## proto部分

```protobuf
//@ 1 使用proto3语法
syntax = "proto3";
//@ 2 生成多个类（一个类便于管理）
option java_multiple_files = false;
//@ 3 定义调用时的java包名
option java_package= "com.chan.proto";
//@ 4 生成外部类名
option java_outer_classname = "CadDetProto";
//@ 6. proto包名称（逻辑包名称）
package CadDetProto;

import "google/protobuf/struct.proto";

//@ 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
service CadDetService{
  rpc Execute (CadDetRequest) returns (CadDetResponse) {}
}

//@ 8 定义请求数据结构
// 位置信息
message Position{
  int32 left_top = 1;
  int32 right_top = 2;
  int32 left_bottom = 3;
  int32 right_bottom = 4;
}

// 属性信息
message Attribute {
  repeated string value = 1;
}

// 检测信息
message DetInfo {
  int32 id = 1;
  Position position = 2;
  Attribute attribute = 3;
}

// 请求数据结构
message CadDetRequest{
  string filePath = 1;
}

// 响应数据结构
message CadDetResponse{
  string file_name = 1;
  repeated DetInfo detInfo = 2;
}
```



## 后端部分

*使用 Grpc 来使 java 与 python 进行通信*

### java 客户端

*主要从 Controller 部分进行介绍*

* 接口一：调用py算法
  * 获得py算法处理的结果，并保留一份数据以便其他接口使用

```java
	private List<CadDetProto.DetInfo> tempData;

    private GrpcScriptExecImpl grpcScriptExec;

    @Autowired
    public void setGrpcScriptExec(GrpcScriptExecImpl grpcScriptExec) {
        this.grpcScriptExec = grpcScriptExec;
    }

    // 调用py接口
    @PostMapping("CallPy")
    public Object script(@RequestBody Map<String, Object> request) {

        String path = (String) request.getOrDefault("path", "");
        if (path.isEmpty()) {
            return "";
        }

        ScriptExecResult scriptExecResult = this.grpcScriptExec.exec(new RequestContent(path));
        String fileName = scriptExecResult.getFileName();
        List<CadDetProto.DetInfo> result = scriptExecResult.getResult();

        this.tempData = result;


        return fileName + " " + result;
    }
```

* 其中，`exec` 的实现方法如下

```java
@Component
public class GrpcScriptExecImpl implements IScriptExec {

    private CadDetServiceGrpc.CadDetServiceBlockingStub cadStub;

    @GrpcClient("CadDetServiceGrpc")
    public void setCadStub(CadDetServiceGrpc.CadDetServiceBlockingStub cadStub) {
        this.cadStub = cadStub;
    }

    @Override
    public ScriptExecResult exec(RequestContent path) {
        // 获取Cad图像的物理路径
        String p = path.getPath();
        // 参数1判空
        // 如果p为空，则返回一个ScriptExecResult对象，结果为null
        if (p.isEmpty()) return new ScriptExecResult();

        // 构建请求
        CadDetProto.CadDetRequest.Builder builder = CadDetProto.CadDetRequest.newBuilder()
                .setFilePath(p);

        // 执行请求
        CadDetProto.CadDetResponse response = cadStub.execute(builder.build());

        // 获取响应结果
        String fileName = response.getFileName();
        List<CadDetProto.DetInfo> detInfoList = response.getDetInfoList();
        ScriptExecResult scriptExecResult = new ScriptExecResult(fileName, detInfoList);

        try {
            return scriptExecResult;
        } catch (io.grpc.StatusRuntimeException e) {
            throw new RuntimeException("script exec error,msg: " + e.getMessage(), e);
        }
    }
}
```

* 接口二：以表格形式展示数据

```java
// 将数据以表格形式返回
    @PostMapping("/getDataForTable")
    public ResponseEntity<List<DetInfoDTO>> getDataForTable() {

        List<DetInfoDTO> dtos = new ArrayList<>();
        for (CadDetProto.DetInfo detInfo : this.tempData) {
            List<Integer> position = Arrays.asList(
                    detInfo.getPosition().getLeftTop(),
                    detInfo.getPosition().getRightTop(),
                    detInfo.getPosition().getRightBottom(),
                    detInfo.getPosition().getLeftBottom()
            );
            List<String> attribute = new ArrayList<>(detInfo.getAttribute().getValueList());
            dtos.add(new DetInfoDTO(detInfo.getId(), position, attribute));
        }
        return ResponseEntity.ok(dtos);
    }
```

* 接口三、四、五、六：数据库CRUD接口

```java
    @Resource
    private CadContentServiceImpl dataService;

    @PostMapping("/create")
        public Object create() {

            if (this.tempData != null) {
                for (CadDetProto.DetInfo obj : tempData) {

                    int id = obj.getId();
                    CadDetProto.Position positions = obj.getPosition();
                    CadDetProto.Attribute attributes = obj.getAttribute();

                    String position = "[" + positions.getLeftTop() + ","
                            + positions.getRightTop() + ","
                            + positions.getRightBottom() + ","
                            + positions.getLeftBottom() + "]";

                    String value = "" + attributes.getValueList();

                    dataService.save(new CadContent(position, value, id));
                }
                tempData = null;
            } else {
                return ResponseEntity.badRequest().body("结果为空或未执行算法");
            }
            return ResponseEntity.ok("数据已存入");
        }

    @GetMapping("/read")
    public Object read() {
        return dataService.list();
    }

    @PutMapping("/update")
    public Object update(@RequestParam Integer id, String position, String value) {

        if (id == null || position == null || position.isEmpty() || value == null || value.isEmpty()) {
            return ResponseEntity.badRequest().body("参数不能为空");
        }
        UpdateWrapper<CadContent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id)
                .set("position", position)
                .set("value", value);
        boolean isUpdate = dataService.update(updateWrapper);
        if (isUpdate) {
            return ResponseEntity.ok("数据更新成功");
        } else {
            return ResponseEntity.badRequest().body("数据更新失败，请检查id是否存在");
        }
    }

    @DeleteMapping("/delete")
    public Object delete(@RequestParam Integer id) {

        if (id == null) {
            return ResponseEntity.badRequest().body("id参数不能为空");
        }
        boolean isDelete = dataService.removeById(id);
        if (isDelete) {
            return ResponseEntity.ok("数据删除成功");
        } else {
            return ResponseEntity.badRequest().body("数据删除失败");
        }

    }
```

### python 服务端

*接收java端的请求，并将算法运行的结果填充消息体，返回响应*

*其中，算法调用的结果假设为列表形式，如*

*位置列表：`[[110, 111, 112, 113], [120, 121, 122, 123], [130, 131, 132, 133]]`*

*识别列表：[["R5", "R5 + 0.1"], ["Φ2.5", "Φ2.5 ± 0.1"], ["Φ3.5", "Φ3.5 ± 0.1"]]*

```python
import sys
import time
from concurrent import futures

import grpc

from Alog.testController import TestController

sys.path.append('D:\python_code\CadDet\proto')
from proto import cadDet_pb2
from proto import cadDet_pb2_grpc


class ScriptServicer(cadDet_pb2_grpc.CadDetServiceServicer):

    def Execute(self, request, context):

        s = request.filePath
        print("Received request: %s" % s)

        # 创建一个 CadDetResponse 对象
        response = cadDet_pb2.CadDetResponse()

        # 填充 file_name 字段
        response.file_name = s

        # 创建Controller对象获取位置和识别信息
        controller = TestController()
        position_data, value_data = controller.test_models(s)

        for i in range(len(position_data)):

            # 在每次循环开始时创建一个新的 DetInfo 对象并填充 det_info 字段
            det_info = response.detInfo.add()

            det_info.id = 1

            data0 = position_data[i]

            # 填充 Position 字段
            det_info.position.left_top = data0[0]
            det_info.position.right_top = data0[1]
            det_info.position.left_bottom = data0[2]
            det_info.position.right_bottom = data0[3]

            data1 = value_data[i]

            # 填充 Attribute 字段
            for j in range(len(data1)):
                det_info.attribute.value.append(data1[j])

        print(response)

        # 返回响应 response
        return response


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    cadDet_pb2_grpc.add_CadDetServiceServicer_to_server(ScriptServicer(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    try:
        while True:
            time.sleep(60 * 60 * 24)  # one day in seconds
    except KeyboardInterrupt:
        server.stop(0)


if __name__ == '__main__':
    serve()

```



## 前端部分

 *前端效果展示*
![frontend_test](.\image\frontend_test.png)



*主要分为5个组件*

1. 选择本地图片路径与调用算法
2. 更新数据
3. 删除数据
4. 展示算法运行结果（暂未添加展示**图片内容**）
5. 读取数据库结果