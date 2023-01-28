package com.library.core.service.utils;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String saveProductImage(MultipartFile imageFile);

    void removeProductImage(String imagePath);

    String updateProductImage(MultipartFile imageFile, String imagePath);

}
