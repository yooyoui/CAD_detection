# CAD_detection

## 一、环境搭建

> 参考博客：[PaddleOCR环境搭建、模型训练、推理、部署全流程（Ubuntu系统）_1_paddlepaddle 训练环境-CSDN博客](https://blog.csdn.net/m0_60657960/article/details/137072289)

### 1、Paddle

* python版本为3.8

  ```python
  conda create -n paddle2.5.2 python=3.8
  ```

  

* Paddlepaddle版本为2.5.2

  ```python
  python -m pip install paddlepaddle-gpu==2.5.2 -i https://pypi.tuna.tsinghua.edu.cn/simple
  ```
  
  * 对应CUDA版本v11.8

    [CUDA Toolkit 11.8 Downloads | NVIDIA Developer](https://developer.nvidia.com/cuda-11-8-0-download-archive)

  * cuDNN版本为v8.6.0

    [cuDNN Archive | NVIDIA Developer](https://developer.nvidia.com/rdp/cudnn-archive)

* ppocr版本为2.7.1（已经将整个文件放在了网盘中）

  [PaddlePaddle/PaddleOCR at release/2.7.1 (github.com)](https://github.com/PaddlePaddle/PaddleOCR/tree/release/2.7.1)

  链接: https://pan.baidu.com/s/1v0JsIAjUcVdUkJOVcyBJKg?pwd=dvka 提取码: dvka

  * 需注意，在该版本中`./ppstructure/utility.py`116行后缺少代码，需自行添加，否则运行PPOCRLabel时会出现 `AttributeError: ‘Namespace’ object has no attribute ‘return_word_box’`错误(run_related中已存放更改后的文件)

        parser.add_argument("--return_word_box", type=str2bool, default=False, help='Whether return the bbox of each word (split by space) or chinese character. Only used in ppstructure for layout recovery')

  * requirement

    ```python
    pip install -r requirements.txt -i https://mirror.baidu.com/pypi/simple/
    ```

* 测试图片存放位置(run_related中已存放该文件)

  `./doc/datasets/cad/`

* 训练后的模型存放位置

  * 检测模型(det)

    `./output/ch_db_res18/`

  * 识别模型(rec)

    `./output/rec_ppocr_v3/`

* 使用PPOCRLabel制作的数据集存放位置(run_related中已存放该文件)

  * det

    `./train_data/det/`

  * rec

    `./train_data/rec/`

* 各种预训练模型存放位置

  * det

    `./ch_PP-OCRv4_det_train/`[^1]

    `./ch_PP-OCRv4_det_server_train/`[^2]

  * rec

    `./ch_PP-OCRv4_rec_train/`[^1]

    `./ch_PP-OCRv4_rec_server_train/`[^2]

* 官方推理模型存放位置

  * det

    `./models/ch_PP-OCRv4_det_server_infer`

  * rec

    `./models/ch_PP-OCRv4_rec_server_infer`

  * 推理模型测试代码

    ```python
    python tools/infer/predict_system.py  --image_dir="./doc/datasets/cad/1.png" --det_model_dir="./models/ch_PP-OCRv4_det_infer/" --rec_model_dir="./models/ch_PP-OCRv4_rec_infer/"
    ```
    
    

### 2、使用PPOCRLabel制作数据集

* `cd PPOCRLabel`

  * requirement

    ```python
    pip install -i https://pypi.tuna.tsinghua.edu.cn/simple -r requirements.txt
    ```

  * 运行PPOCRLabel

    ```python
    python PPOCRLabel.py --lang ch
    ```

    * 文件→打开目录→选择图片存放的位置即可开始制作数据集
    * 快捷键可在帮助中找到
    * PaddleOCR可使用自动标注

  * 做好标注和识别的结果修改后，运行以下代码对数据集进行划分

    ```python
    python gen_ocr_train_val_test.py --trainValTestRatio 6:2:2 --datasetRootPath ../doc/datasets/cad
    ```
  
    * 从而得到det和rec两种数据集，存放至
    
      `ppocr2.7.1/train_data/`

### 3、模型训练

* 配置文件(run_related中已存放该文件)

  * det

    `.configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml`

    * 训练代码

      ```python
      python tools/train.py -c configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml
      ```

  * rec

    `.configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml`

    * 训练代码

      ```python
      python tools/train.py -c configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml
      ```

* 模型测试（训练后的模型过大，暂未上传）

  * det

    ```python
    python tools/infer_det.py -c configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml -o Global.pretrained_model=output/ch_db_res18/best_model/model.pdparams Global.infer_img="./train_data/det/test/2.png"
    ```

  * rec

    ```python
    python tools/infer_rec.py -c configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml -o Global.pretrained_model=output/rec_ppocr_v3/best_accuracy.pdparams Global.infer_img="./train_data/rec/test/1_crop_0.jpg"
    ```

* 转换成推理模型

  由于测试效果并不好，暂未转换成推理模型，但可使用如下命令转换

  * det

    ```python
    python tools/export_model.py -c "./configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml" -o Global.pretrained_model="./output/ch_db_res18/best_model/model.pdparams" Global.save_inference_dir="./inference_model/det/"
    ```

  * rec

    ```python
    python tools/export_model.py -c "./configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml" -o Global.pretrained_model="./output/rec_ppocr_v3/best_accuracy.pdparams" Global.save_inference_dir="./inference_model/rec/"
    ```

  * 再使用predict_system.py进行验证

    ```python
    python tools/infer/predict_system.py --image_dir="./path/to/image" 
    
    --det_model_dir="./inference_model/det/" 
    
    --rec_model_dir="./inference_model/rec"
    ```

[^1]: 轻量级模型
[^2]: 服务端模型

## 二、使用Grpc来在java中调用python脚本

### 1. protobuf文件的编写

*整体代码*

```protobuf
//@ 1 使用proto3语法
syntax = "proto3";
//@ 2 生成多个类（一个类便于管理）
option java_multiple_files = false;
//@ 3 定义调用时的java包名
option java_package= "com.chan.proto";
//@ 4 生成外部类名
option java_outer_classname = "CallPyProto";
//@ 6. proto包名称（逻辑包名称）
package CallPyProto;

import "google/protobuf/struct.proto";

//@ 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
service CallPyService{
  rpc Execute (ScriptRequest) returns (ScriptResponse) {}
}

//@ 8 定义请求数据结构
//字符串数据类型
//等号后面的数字即索引值（表示参数顺序，以防止参数传递顺序混乱），服务启动后无法更改
//不能使用19000-1999保留数字
message ScriptRequest{
  string content = 1;
}
//@ 9 定义响应数据结构
message ScriptResponse{
  string result = 1;
}
```

### 2. 依赖

*使用了Maven进行依赖管理*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.chan</groupId>
    <artifactId>grpc</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>grpc</name>
    <description>grpc</description>
    <properties>
        <os.detected.classifier>windows-x86_64</os.detected.classifier>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>annotationProcessor</scope>
        </dependency>

        <!-- Mybatis-Plus Spring Boot Starter-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
            <version>3.5.5</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.5.7</version>
        </dependency>

        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.3</version>
        </dependency>

        <!-- SQLite数据库驱动 -->
        <!-- https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc -->
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.42.0.0</version>
        </dependency>

        <!--druid数据库连接池依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.8</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/net.devh/grpc-client-spring-boot-starter -->
        <dependency>
            <groupId>net.devh</groupId>
            <artifactId>grpc-client-spring-boot-starter</artifactId>
            <version>3.1.0.RELEASE</version>
        </dependency>

        <!-- gRPC -->
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-netty-shaded</artifactId>
            <version>1.64.0</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-protobuf</artifactId>
            <version>1.64.0</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-stub</artifactId>
            <version>1.64.0</version>
        </dependency>

        <dependency> <!-- necessary for Java 9+ -->
            <groupId>org.apache.tomcat</groupId>
            <artifactId>annotations-api</artifactId>
            <version>6.0.53</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <!-- Protobuf Maven Plugin -->
            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.6.1</version>
                <configuration>
                    <protocArtifact>com.google.protobuf:protoc:3.25.1:exe:${os.detected.classifier}</protocArtifact>
                    <pluginId>grpc-java</pluginId>
                    <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.64.0:exe:${os.detected.classifier}</pluginArtifact>
                    <outputDirectory>${basedir}/src/main/java</outputDirectory>
                    <clearOutputDirectory>false</clearOutputDirectory>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>compile-custom</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

### 3. 服务配置：application.yml

```yaml
# gpc client config
grpc:
  client:
    CallPyServiceGrpc:
      address: 'static://127.0.0.1:50051'
      negotiationType: plaintext

# DataSource Config
spring:
  datasource:
    url: jdbc:sqlite:D:\java_code\grpc\db\CatDetData.db
    driver-class-name: org.sqlite.JDBC
    username:
    password:
server:
  port: 8080
```

### 4. Java部分代码

*详见JavaCode*

* ApiController中定义了5个接口
  * 1个调用py脚本的接口，参数为图片路径
  * 4个调用数据库CRUD操作的接口

### 5. Python部分代码

* 需要安装protobuf插件

* 需要安装依赖

  * ```python
    pip install grpcio
    pip install grpcio-tools googleapis-common-protos
    ```

  * ```python
    # 入片代码
    python -m grpc_tools.protoc -I . --python_out=. --grpc_python_out=. xxx.proto
    ```

    * 此时生成两个文件`xxx_pb2_grpc.py`和`xxx_pb2.py`

#### 客户端代码

*详见python_code*

* CallPy_server为运行的主程序
* 在ScriptExe中
  * AlgoController 为算法的总控
  * ppOcr 为算子1
  * Config 为配置文件

## 三、前端部分

*详见frontend*

目前暂时只写了调用5个接口对应的操作

