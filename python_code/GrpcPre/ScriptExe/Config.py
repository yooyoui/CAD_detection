class ConfigManager:
    def __init__(self):
        self.config = {
            "det_model_dir": "--det_model_dir=D:/ppocr/ppocr2.7.1/models/ch_PP-OCRv4_det_infer/",
            "rec_model_dir": "--rec_model_dir=D:/ppocr/ppocr2.7.1/models/ch_PP-OCRv4_rec_infer/",
            "python_path": "D:/software/anaconda/envs/ppocr2.5.2/python",
            "script_path": "tools/infer/predict_system.py",
            "ppocr_dir": "D:/ppocr/ppocr2.7.1",
            "algorithm_type": "ppocr"
        }

    def get_config(self, key):
        return self.config.get(key, None)
