package com.library.facade.populator.impl;

import com.library.core.model.Order;
import com.library.facade.dto.CheckoutDto;
import com.library.facade.populator.Populator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class ReverseCheckoutPopulator implements Populator<CheckoutDto, Order> {

    @Override
    public void populate(CheckoutDto checkoutDto, Order order) {
        order.setName(checkoutDto.getName());
        order.setAddress(checkoutDto.getAddress());
        order.setPhoneNo(checkoutDto.getPhoneNo());
        order.setPaymentMethod(checkoutDto.getPaymentMethod());
        order.setTotalPrice(checkoutDto.getTotalPrice());
    }
}
