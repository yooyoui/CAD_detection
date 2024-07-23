import sys

sys.path.append('D:\python_code\CadDet\proto')
# process_data.py
from proto import cadDet_pb2


def process_position_data(position_data, value_data):
    det_info_list = []

    for i in range(len(position_data)):
        # 创建一个新的 DetInfo 对象并填充 det_info 字段
        det_info = cadDet_pb2.CadDetResponse.DetInfo()
        det_info.id = i

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

        det_info_list.append(det_info)

    return det_info_list
