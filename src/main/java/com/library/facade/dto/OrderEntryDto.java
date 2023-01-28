package com.library.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntryDto {

    private Integer id;

    @NotBlank(message = "Invalid quantity!")
    private Integer quantity;

    private ProductCustomerDto productCustomerDto;
}
