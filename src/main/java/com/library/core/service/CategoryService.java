package com.library.core.service;

import com.library.core.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    boolean save(Category category);

    boolean update(Category newCategory);

    boolean deleteById(Integer id);

    Optional<Category> getById(Integer id);

    List<Category> getAll();
}
