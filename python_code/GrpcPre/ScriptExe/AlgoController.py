from ScriptExe.ppOcr import PpOcr


class AlgorithmController:
    def __init__(self, config_manager):
        self.config_manager = config_manager
        self.algo = self.select_algorithm()

    def select_algorithm(self):
        algorithm_type = self.config_manager.get_config("algorithm_type")
        if algorithm_type == "ppocr":
            print("ppocr")
            return PpOcr()
        # Add more algorithms here
        else:
            raise ValueError("Unsupported algorithm type")

    def run(self, image_dir):
        det_model_dir = self.config_manager.get_config("det_model_dir")
        rec_model_dir = self.config_manager.get_config("rec_model_dir")
        python_path = self.config_manager.get_config("python_path")
        script_path = self.config_manager.get_config("script_path")
        ppocr_dir = self.config_manager.get_config("ppocr_dir")

        result = self.algo.run(image_dir, det_model_dir, rec_model_dir, python_path, script_path, ppocr_dir)

        return result
