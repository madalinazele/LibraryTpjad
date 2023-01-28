package com.library.facade.dto;

import com.library.core.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @NotBlank(message = "Invalid name!")
    private String name;

    @NotBlank(message = "Invalid address!")
    private String address;

    @NotBlank(message = "Invalid phoneNo!")
    private String phoneNo;

    @NotBlank(message = "Invalid payment method!")
    private PaymentMethod paymentMethod;

    private List<OrderEntryDto> orderEntryDtoList;

    private Double totalPrice;
}
