from Alog.det import DetModel
from Alog.rec import RecModel


class TestController:
    def __init__(self):
        pass

    @staticmethod
    def test_models(path):
        # 创建模型实例
        det_model = DetModel()
        rec_model = RecModel()

        # 使用det模型检测文字位置
        positions = det_model.detect(path)

        # 使用rec模型识别位置内容
        results = rec_model.recognize(positions)

        return positions, results

