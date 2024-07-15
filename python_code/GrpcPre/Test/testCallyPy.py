import json
import sys

from Test.Alog.testController import test_models

sys.path.append('D:\python_code\GrpcPre\proto')

import grpc
import CallPy_pb2
import CallPy_pb2_grpc
from concurrent import futures
import time

from ScriptExe.AlgoController import AlgorithmController
from ScriptExe.Config import ConfigManager

_ONE_DAY_IN_SECONDS = 60 * 60 * 24


# service impl
class ScriptServicer(CallPy_pb2_grpc.CallPyServiceServicer):

    def Execute(self, request, context):
        s = request.content
        print("content: %s" % s)

        # 执行算法
        result = test_models(s)

        return CallPy_pb2.ScriptResponse(result=json.dumps(result))


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    CallPy_pb2_grpc.add_CallPyServiceServicer_to_server(ScriptServicer(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    try:
        while True:
            time.sleep(_ONE_DAY_IN_SECONDS)
    except KeyboardInterrupt:
        server.stop(0)


if __name__ == '__main__':
    serve()
