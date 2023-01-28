package com.library.core.service.impl;

import com.library.core.model.Product;
import com.library.core.repository.ProductRepository;
import com.library.core.service.ProductService;
import com.library.core.utils.PaginatedResult;
import com.library.core.repository.dto.ProductFilterAndSortDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private static final String PRODUCT_NOT_FOUND = "Product with id {} not found!";

    private final ProductRepository productRepository;

    @Override
    public boolean save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean update(Product newProduct) {
        if (!productRepository.update(newProduct)) {
            log.error(PRODUCT_NOT_FOUND, newProduct.getId());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        if (!productRepository.deleteById(id)) {
            log.error(PRODUCT_NOT_FOUND, id);
            return false;
        }
        return true;
    }

    @Override
    public Optional<Product> getById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product;
        }
        log.error(PRODUCT_NOT_FOUND, id);
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByCategoryId(Integer id) {
        return productRepository.getAllByCategoryId(id);
    }

    @Override
    public PaginatedResult<Product> search(ProductFilterAndSortDto productFilterAndSortDto, Integer page) {
        return productRepository.search(productFilterAndSortDto, page);
    }

    @Override
    public int count(ProductFilterAndSortDto productFilterAndSortDtoSelected) {
        return productRepository.count(productFilterAndSortDtoSelected);
    }

}
