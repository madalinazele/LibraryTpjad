package com.library.core.service.utils.impl;

import com.library.core.exception.InvalidFileException;
import com.library.core.service.utils.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class DefaultFileUploadService implements FileUploadService {
    public static final String DELIMITER = "/";
    private final Environment environment;
    private final String imagesLocation;

    public DefaultFileUploadService(Environment environment) {
        this.environment = environment;
        this.imagesLocation = environment.getProperty("upload.product_images_location");
    }

    @Override
    public String saveProductImage(MultipartFile imageFile) {
        try {
            if (imageFile.isEmpty()) {
                throw new IOException();
            }
            String fileName = getTimeStamp() + "_" + imageFile.getOriginalFilename();
            Path filepath = Paths.get(imagesLocation + DELIMITER + fileName);
            imageFile.transferTo(filepath);

            return fileName;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new InvalidFileException(e.getMessage());
        }
    }

    @Override
    public String updateProductImage(MultipartFile imageFile, String imagePath) {
        try {
            Files.deleteIfExists(Paths.get(imagesLocation + "/" + imagePath));
            return saveProductImage(imageFile);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new InvalidFileException(e.getMessage());
        }
    }

    @Override
    public void removeProductImage(String imagePath) {
        try {
            File image = new File(imagesLocation + "/" + imagePath);
            if (!image.exists()) {
                throw new IOException();
            }
            if (image.delete()) {
                log.info("Image deleted successfully");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    private static String getTimeStamp() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(new Date());
    }
}
