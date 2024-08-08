import io
import sys
import time
from concurrent import futures
from PIL import Image

import grpc

from Alog.testController import TestController

sys.path.append('D:\dev_code\python\CadDet\proto')
from proto import cadDet_pb2
from proto import cadDet_pb2_grpc


class ScriptServicer(cadDet_pb2_grpc.CadDetServiceServicer):

    def Execute(self, request, context):

        s = request.origImage

        data = io.BytesIO(s)
        image = Image.open(data)
        image.save("test.png")

        # 创建一个 CadDetResponse 对象
        response = cadDet_pb2.CadDetResponse()

        # 填充 file_name 字段
        response.file_name = "test.png"

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

        # 将字节流赋值给 response.resultImage
        # 这里只是一个示例，实际需要传输的图片需要根据实际情况编写
        # 这里仅将传送过来的图片字节流返回
        response.resultImage = s

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
