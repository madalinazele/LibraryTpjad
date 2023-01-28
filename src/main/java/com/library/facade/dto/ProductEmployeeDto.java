package com.library.facade.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEmployeeDto {

    private Integer id;

    @NotBlank(message = "Invalid name!")
    private String name;

    @Min(value = 0, message = "The price is not valid!")
    private Double price;

    private String imagePath;

    @Min(value = 0, message = "The stock is not valid!")
    private Integer stock;

    @Min(value = 0, message = "The part number is not valid!")
    private Integer manufacturerPartNumber;

    @NotBlank(message = "Invalid description!")
    private String description;

    private MultipartFile imageFile;

    //TODO change Category with CategoryDTO
    private CategoryDto category;
}
