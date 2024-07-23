# test.py

import sys
import time
from concurrent import futures

import grpc

from Alog.testController import TestController
from Alog.process_data import process_position_data

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

        # 创建Controller对象获取位置信息
        controller = TestController()
        position_data, value_data = controller.test_models(s)

        # 调用 process_position_data 函数处理数据
        det_info_list = process_position_data(position_data, value_data)

        # 将处理后的 det_info 对象添加到 response 中
        for det_info in det_info_list:
            response.detInfo.extend([det_info])

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