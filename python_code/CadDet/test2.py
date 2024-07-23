import sys
import time
from concurrent import futures

import grpc

sys.path.append('D:\python_code\CadDet\proto')
import cadDet_pb2
import cadDet_pb2_grpc


class ScriptServicer(cadDet_pb2_grpc.CadDetServiceServicer):

    def Execute(self, request, context):
        print("Received request: %s" % request.filePath)
        # 创建一个 CadDetResponse 对象
        response = cadDet_pb2.CadDetResponse()

        # 填充字段
        response.file_name = "test_file"

        # 创建一个 DetInfo 对象并填充 det_info 字段
        det_info = response.detInfo.add()
        det_info.id = 1

        # 填充 Position 字段
        det_info.position.left_top = 0
        det_info.position.right_top = 1
        det_info.position.left_bottom = 2
        det_info.position.right_bottom = 3

        # 填充 Attribute 字段
        det_info.attribute.value.extend(["attribute1", "attribute2"])

        # 返回 response
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
