# CAD_detection

## 一、环境搭建

参考博客：[PaddleOCR环境搭建、模型训练、推理、部署全流程（Ubuntu系统）_1_paddlepaddle 训练环境-CSDN博客](https://blog.csdn.net/m0_60657960/article/details/137072289)

### 1、Paddle

* python版本为3.8

  `conda create -n paddle2.5.2 python=3.8`

* Paddlepaddle版本为2.5.2

  `python -m pip install paddlepaddle-gpu==2.5.2 `

  `-i https://pypi.tuna.tsinghua.edu.cn/simple`

  * 对应CUDA版本v11.8

    [CUDA Toolkit 11.8 Downloads | NVIDIA Developer](https://developer.nvidia.com/cuda-11-8-0-download-archive)

  * cuDNN版本为v8.6.0

    [cuDNN Archive | NVIDIA Developer](https://developer.nvidia.com/rdp/cudnn-archive)

* ppocr版本为2.7.1（已经将整个文件放在了网盘中）

  [PaddlePaddle/PaddleOCR at release/2.7.1 (github.com)](https://github.com/PaddlePaddle/PaddleOCR/tree/release/2.7.1)

  链接: https://pan.baidu.com/s/1v0JsIAjUcVdUkJOVcyBJKg?pwd=dvka 提取码: dvka

  * requirement

    `pip install -r requirements.txt -i https://mirror.baidu.com/pypi/simple/`

  * 若从github上clone，需注意2.7.1版本在运行时存在bug，导致PPOCRLabel出现List index out of range的错误，此时需将`./ppstructure/utility.py`的第116行后加入

        parser.add_argument("--return_word_box", type=str2bool, default=False, help='Whether return the bbox of each word (split by space) or chinese character. Only used in ppstructure for layout recovery')

* 测试图片存放位置

  `./doc/datasets/cad/`

* 训练后的模型存放位置

  * 检测模型(det)

    `./output/ch_db_res18/`

  * 识别模型(rec)

    `./output/rec_ppocr_v3/`

* 使用PPOCRLabel制作的数据集存放位置

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

    `python tools/infer/predict_system.py  --image_dir="./doc/datasets/cad/1.png" --det_model_dir="./models/ch_PP-OCRv4_det_infer/" --rec_model_dir="./models/ch_PP-OCRv4_rec_infer/"`

### 2、使用PPOCRLabel制作数据集

* `cd PPOCRLabel`

  * requirement

    `pip install -i https://pypi.tuna.tsinghua.edu.cn/simple -r requirements.txt`

  * 运行PPOCRLabel

    `python PPOCRLabel.py --lang ch`

    * 文件→打开目录→选择图片存放的位置即可开始制作数据集
    * 快捷键可在帮助中找到
    * PaddleOCR可使用自动标注

  * 做好标注和识别的结果修改后，运行以下代码对数据集进行划分

    `python gen_ocr_train_val_test.py --trainValTestRatio 6:2:2 --datasetRootPath ../doc/datasets/cad`

    * 从而得到det和rec两种数据集，存放至

      `ppocr2.7.1/train_data/`

### 3、模型训练

* 配置文件

  * det

    `.configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml`

    * 训练代码

      `python tools/train.py -c configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml`

  * rec

    `.configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml`

    * 训练代码

      `python tools/train.py -c configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml`

* 模型测试（训练后的模型过大，暂未上传）

  * det

    `python tools/infer_det.py -c configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml -o Global.pretrained_model=output/ch_db_res18/best_model/model.pdparams Global.infer_img="./train_data/det/test/2.png"`

  * rec

    ` python tools/infer_rec.py -c configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml -o Global.pretrained_model=output/rec_ppocr_v3/best_accuracy.pdparams Global.infer_img="./train_data/rec/test/1_crop_0.jpg"`

* 转换成推理模型

  由于测试效果并不好，暂未转换成推理模型，但可使用如下命令转换

  * det

    `python tools/export_model.py -c "./configs/det/ch_ppocr_v2.0/ch_det_res18_db_v2.0.yml" -o Global.pretrained_model="./output/ch_db_res18/best_model/model.pdparams" Global.save_inference_dir="./inference_model/det/"`

  * rec

    `python tools/export_model.py -c "./configs/rec/PP-OCRv3/ch_PP-OCRv3_rec.yml" -o Global.pretrained_model="./output/rec_ppocr_v3/best_accuracy.pdparams" Global.save_inference_dir="./inference_model/rec/"`

  * 再使用predict_system.py进行验证

    `python tools/infer/predict_system.py --image_dir="./path/to/image" `

    `--det_model_dir="./inference_model/det/" `

    `--rec_model_dir="./inference_model/rec"`

[^1]: 轻量级模型
[^2]: 服务端模型