package com.library.facade.populator.impl;

import com.library.core.model.Product;
import com.library.facade.dto.CategoryDto;
import com.library.facade.dto.ProductCustomerDto;
import com.library.facade.populator.Populator;

public class ProductDtoDetailsPopulator implements Populator<Product, ProductCustomerDto> {
    @Override
    public void populate(Product product, ProductCustomerDto productCustomerDto) {
        productCustomerDto.setId(product.getId());
        productCustomerDto.setDescription(product.getDescription());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setName(product.getCategory().getName());

        productCustomerDto.setCategory(categoryDto);
        productCustomerDto.setStock(product.getStock());
        productCustomerDto.setManufacturerPartNumber(product.getManufacturerPartNumber());
    }
}
