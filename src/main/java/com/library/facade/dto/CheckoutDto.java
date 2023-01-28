package com.library.facade.dto;

import com.library.core.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDto {

    @NotBlank(message = "Invalid name!")
    private String name;

    @NotBlank(message = "Invalid address!")
    private String address;

    @NotBlank(message = "Invalid phoneNo!")
    private String phoneNo;

    @NotBlank(message = "Invalid payment method!")
    private PaymentMethod paymentMethod;

    @NotBlank(message = "Invalid price!")
    private Double totalPrice;
}
