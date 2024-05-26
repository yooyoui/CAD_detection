def remove_empty_lines(input_file_path, output_file_path):
    with open(input_file_path, 'r', encoding='utf-8') as input_file:
        lines = input_file.readlines()
    with open(output_file_path, 'w', encoding='utf-8') as output_file:
        for line in lines:
            if line.strip():  # If line is not empty
                output_file.write(line)

# Replace 'train.txt' with your actual file paths
remove_empty_lines('test.txt', 'test.txt')