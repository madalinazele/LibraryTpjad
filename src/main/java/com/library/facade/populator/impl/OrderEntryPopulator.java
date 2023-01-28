package com.library.facade.populator.impl;

import com.library.core.model.CartEntry;
import com.library.core.model.OrderEntry;
import com.library.facade.populator.Populator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class OrderEntryPopulator implements Populator<CartEntry, OrderEntry> {

    @Override
    public void populate(CartEntry cartEntry, OrderEntry orderEntry) {
        orderEntry.setQuantity(cartEntry.getQuantity());
        orderEntry.setProduct(cartEntry.getProduct());
    }
}
