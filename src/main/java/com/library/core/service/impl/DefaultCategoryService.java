package com.library.core.service.impl;

import com.library.core.model.Category;
import com.library.core.model.Product;
import com.library.core.repository.CategoryRepository;
import com.library.core.service.CategoryService;
import com.library.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Override
    public boolean save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean update(Category newCategory) {
        return categoryRepository.update(newCategory);
    }

    @Override
    public boolean deleteById(Integer id) {
        removeCategoryFromProducts(id);
        return categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    private void removeCategoryFromProducts(Integer id) {
        List<Product> allProductsByCategoryId = productService.getAllByCategoryId(id);
        if (isNotEmpty(allProductsByCategoryId)) {
            allProductsByCategoryId.forEach(product -> {
                product.setCategory(null);
                productService.update(product);
            });
        }
    }
}
