package com.library.facade.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CartDto {

    private List<CartEntryDto> cartEntryDtoList;

    private Double totalPrice;
}
