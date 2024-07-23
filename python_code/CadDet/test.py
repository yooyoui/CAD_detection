import sys
import time
from concurrent import futures

import grpc

from Alog.det import DetModel
from Alog.rec import RecModel
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

        print("response.detInfo: ", response.detInfo)

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
