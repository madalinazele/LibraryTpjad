package com.library.core.repository;

import com.library.core.model.Product;
import com.library.core.utils.PaginatedResult;
import com.library.core.repository.dto.ProductFilterAndSortDto;

import java.util.List;

public interface ProductRepository extends AbstractRepository<Product, Integer> {
    List<Product> getAllByCategoryId(Integer id);

    PaginatedResult<Product> search(ProductFilterAndSortDto productFilterAndSortDto, Integer page);

    int count(ProductFilterAndSortDto productFilterAndSortDto);
}
