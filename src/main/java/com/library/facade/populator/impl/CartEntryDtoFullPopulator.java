package com.library.facade.populator.impl;

import com.library.core.model.CartEntry;
import com.library.core.model.Product;
import com.library.facade.converter.Converter;
import com.library.facade.dto.CartEntryDto;
import com.library.facade.dto.ProductCustomerDto;
import com.library.facade.populator.Populator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class CartEntryDtoFullPopulator implements Populator<CartEntry, CartEntryDto> {

    @Qualifier("productDtoConverter")
    private final Converter<Product, ProductCustomerDto> productDtoConverter;

    @Override
    public void populate(CartEntry cartEntry, CartEntryDto cartEntryDto) {
        cartEntryDto.setId(cartEntry.getId());
        cartEntryDto.setQuantity(cartEntry.getQuantity());
        Product product = cartEntry.getProduct();
        ProductCustomerDto productCustomerDto = productDtoConverter.convert(product);
        cartEntryDto.setProductCustomerDto(productCustomerDto);
    }
}
