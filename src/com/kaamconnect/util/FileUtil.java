package com.kaamconnect.util;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUtil {

    private FileUtil() {
    }

    public static File chooseImage(Window window) {

        FileChooser chooser = new FileChooser();

        chooser.setTitle("Select Image");

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Images",
                        "*.jpg",
                        "*.jpeg",
                        "*.png"
                )
        );

        return chooser.showOpenDialog(window);
    }

    public static String copyFile(
            File source,
            String destinationFolder) throws IOException {

        File folder = new File(destinationFolder);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        String extension =
                getFileExtension(source.getName());

        String uniqueName =
                UUID.randomUUID() + "." + extension;

        File destination =
                new File(folder, uniqueName);

        Files.copy(
                source.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING
        );

        return destination.getAbsolutePath();
    }

    public static boolean deleteFile(String path) {

        File file = new File(path);

        return file.exists() && file.delete();
    }

    public static boolean fileExists(String path) {

        return new File(path).exists();
    }

    public static String getFileExtension(String fileName) {

        int index = fileName.lastIndexOf(".");

        if (index == -1)
            return "";

        return fileName.substring(index + 1);
    }

}