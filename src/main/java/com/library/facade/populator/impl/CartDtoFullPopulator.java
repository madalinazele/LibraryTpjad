package com.library.facade.populator.impl;

import com.library.core.model.Cart;
import com.library.core.model.CartEntry;
import com.library.facade.converter.Converter;
import com.library.facade.dto.CartDto;
import com.library.facade.dto.CartEntryDto;
import com.library.facade.populator.Populator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class CartDtoFullPopulator implements Populator<Cart, CartDto> {

    @Qualifier("cartEntryDtoConverter")
    private final Converter<CartEntry, CartEntryDto> cartEntryDtoConverter;

    @Override
    public void populate(Cart cart, CartDto cartDto) {
        Double totalPrice = 0.0;
        List<CartEntry> cartEntries = cart.getCartEntries();
        if (cartEntries != null) {
            totalPrice = cartEntries.stream().mapToDouble(cartEntry -> cartEntry.getProduct().getPrice() * cartEntry.getQuantity()).sum();
            cartDto.setTotalPrice(Math.round(totalPrice * 100.0) / 100.0);
            Collection<CartEntryDto> cartEntryDtos = getCartEntryDtoConverter().convertAll(cartEntries);
            cartDto.setCartEntryDtoList((List<CartEntryDto>) cartEntryDtos);
        } else {
            cartDto.setTotalPrice(totalPrice);
            cartDto.setCartEntryDtoList(new ArrayList<>());
        }
    }
}
