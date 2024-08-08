package com.det.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageSaver {
    public static void saveImage(byte[] image, String outputPath) throws IOException {
        Path path = Paths.get(outputPath);
        Files.write(path, image);
    }
}
