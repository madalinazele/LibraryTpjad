package com.library.facade.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotBlank(message = "Invalid id!")
    private Integer id;

    @NotBlank(message = "Invalid name!")
    private String name;

    @NotNull(message = "Invalid description!")
    private String description;
}
