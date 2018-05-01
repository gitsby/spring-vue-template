package com.github.gitsby.spring_vue_template.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty() || "null".equals(string);
    }

    public static String getFileExtensionFromName(String filename) {
        String extension = "";
        if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
            extension = filename.substring(filename.lastIndexOf(".") + 1);
        }
        return extension;
    }


    public static String capitalizeFirstChar(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
