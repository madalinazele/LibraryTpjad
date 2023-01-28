package com.library.facade.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustomerDto {

    @NotBlank(message = "Invalid id!")
    private Integer id;

    @NotBlank(message = "Invalid name!")
    private String name;

    @NotBlank(message = "Invalid price!")
    @Min(value = 0, message = "The price is not valid!")
    private Double price;

    @NotBlank(message = "Invalid name!")
    private String imagePath;

    @NotBlank(message = "Invalid stock!")
    @Min(value = 0, message = "The stock is not valid!")
    private Integer stock;

    @NotBlank(message = "Invalid manufacturer part number!")
    private Integer manufacturerPartNumber;

    @NotBlank(message = "Invalid description!")
    private String description;
    private MultipartFile imageFile;

    private CategoryDto category;
}
