package com.library.facade;

import com.library.facade.dto.CheckoutDto;
import com.library.facade.dto.OrderDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderFacade {

    CheckoutDto buildCheckoutDto(String email, Double totalPrice);

    OrderDto checkout(CheckoutDto checkoutDto, HttpServletRequest request, HttpServletResponse response);
}
