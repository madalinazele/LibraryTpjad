package com.library.facade.populator.impl;

import com.library.core.model.OrderEntry;
import com.library.core.model.Product;
import com.library.facade.converter.Converter;
import com.library.facade.dto.OrderEntryDto;
import com.library.facade.dto.ProductCustomerDto;
import com.library.facade.populator.Populator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class OrderEntryDtoPopulator implements Populator<OrderEntry, OrderEntryDto> {

    @Qualifier("productDtoConverter")
    private final Converter<Product, ProductCustomerDto> productDtoConverter;

    @Override
    public void populate(OrderEntry orderEntry, OrderEntryDto orderEntryDto) {
        orderEntryDto.setId(orderEntry.getId());
        orderEntryDto.setQuantity(orderEntry.getQuantity());
        orderEntryDto.setProductCustomerDto(productDtoConverter.convert(orderEntry.getProduct()));
    }
}
