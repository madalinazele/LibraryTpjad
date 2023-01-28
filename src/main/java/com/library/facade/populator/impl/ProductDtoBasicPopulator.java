package com.library.facade.populator.impl;

import com.library.core.model.Product;
import com.library.facade.dto.ProductCustomerDto;
import com.library.facade.populator.Populator;

public class ProductDtoBasicPopulator implements Populator<Product, ProductCustomerDto> {
    @Override
    public void populate(Product product, ProductCustomerDto productCustomerDto) {

        productCustomerDto.setName(product.getName());
        productCustomerDto.setImagePath(product.getImagePath());
        productCustomerDto.setPrice(product.getPrice());
    }
}
