import subprocess


class PpOcr:
    def run(self, image_dir, det_model_dir, rec_model_dir, python_path, script_path, ppocr_dir):
        command = f"{python_path} {script_path} {image_dir} {det_model_dir} {rec_model_dir}"
        result = subprocess.run(command, cwd=ppocr_dir, capture_output=True, text=True)
        with open('log.txt', 'w') as f:
            f.write(result.stdout)
            f.write(result.stderr)

        return result.stdout
