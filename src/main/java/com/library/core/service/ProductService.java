package com.library.core.service;

import com.library.core.model.Product;
import com.library.core.utils.PaginatedResult;
import com.library.core.repository.dto.ProductFilterAndSortDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    boolean save(Product product);

    boolean update(Product newProduct);

    boolean delete(Integer id);

    Optional<Product> getById(Integer id);

    List<Product> getAll();

    List<Product> getAllByCategoryId(Integer id);

    PaginatedResult<Product> search(ProductFilterAndSortDto productFilterAndSortDto, Integer page);

    int count(ProductFilterAndSortDto productFilterAndSortDtoSelected);
}

