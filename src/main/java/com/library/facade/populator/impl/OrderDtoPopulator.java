package com.library.facade.populator.impl;

import com.library.core.model.Order;
import com.library.core.model.OrderEntry;
import com.library.facade.converter.Converter;
import com.library.facade.dto.OrderDto;
import com.library.facade.dto.OrderEntryDto;
import com.library.facade.populator.Populator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class OrderDtoPopulator implements Populator<Order, OrderDto> {

    @Qualifier("orderEntryDtoConverter")
    private final Converter<OrderEntry, OrderEntryDto> orderEntryDtoConverter;

    @Override
    public void populate(Order order, OrderDto orderDto) {
        orderDto.setAddress(order.getAddress());
        orderDto.setName(order.getName());
        orderDto.setPhoneNo(order.getPhoneNo());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setTotalPrice(order.getTotalPrice());

        Collection<OrderEntryDto> orderEntryDtos = orderEntryDtoConverter.convertAll(order.getOrderEntries());
        orderDto.setOrderEntryDtoList((List<OrderEntryDto>) orderEntryDtos);
    }
}
