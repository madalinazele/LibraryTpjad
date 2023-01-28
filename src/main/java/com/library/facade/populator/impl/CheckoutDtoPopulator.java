package com.library.facade.populator.impl;

import com.library.core.model.Order;
import com.library.facade.dto.CheckoutDto;
import com.library.facade.populator.Populator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class CheckoutDtoPopulator implements Populator<Order, CheckoutDto> {

    @Override
    public void populate(Order order, CheckoutDto checkoutDto) {
        checkoutDto.setName(order.getName());
        checkoutDto.setAddress(order.getAddress());
        checkoutDto.setPhoneNo(order.getPhoneNo());
        checkoutDto.setPaymentMethod(order.getPaymentMethod());
        checkoutDto.setTotalPrice(order.getTotalPrice());
    }
}
