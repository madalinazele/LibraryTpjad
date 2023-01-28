package com.library.core.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static boolean isImage(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        String mimeType = file.getContentType();
        if (mimeType == null) {
            return false;
        }
        String type = mimeType.split("/")[0];
        return type.equals("image");
    }
}
