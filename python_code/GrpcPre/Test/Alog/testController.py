from Test.Alog.det import DetModel
from Test.Alog.rec import RecModel


def test_models(s):
    # 创建模型实例
    det_model = DetModel()
    rec_model = RecModel()

    # 模拟输入数据
    data = "模拟数据"

    # 使用det模型检测文字位置
    positions = det_model.detect(data)

    # 使用rec模型识别位置内容
    results = rec_model.recognize(positions)

    # 创建一个字典，将位置和结果作为键值对存储
    position_result_dict = {str(position): result for position, result in zip(positions, results)}

    # 打印结果
    print("检测到的位置：", positions)
    print("识别的内容：", results)
    print("位置和结果的字典：", position_result_dict)

    return position_result_dict
