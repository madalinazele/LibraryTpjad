package com.library.facade.impl;

import com.library.core.model.Category;
import com.library.core.service.CategoryService;
import com.library.facade.CategoryFacade;
import com.library.facade.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Component
@AllArgsConstructor
public class DefaultCategoryFacade implements CategoryFacade {
    private final CategoryService categoryService;

    @Override
    public List<CategoryDto> getAll() {
        return categoryService.getAll().stream().map
                        (this::convertToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> getById(Integer id) {
        final Optional<Category> category = categoryService.getById(id);
        return category.map(this::convertToCategoryDto);
    }

    @Override
    public boolean save(CategoryDto categoryDto) {
        final Category category = convertToCategory(categoryDto);
        return categoryService.save(category);
    }

    @Override
    public boolean update(CategoryDto newCategory) {
        Category category = convertToCategory(newCategory);
        return categoryService.update(category);
    }

    @Override
    public boolean delete(Integer id) {
        return categoryService.deleteById(id);
    }

    private CategoryDto convertToCategoryDto(Category category) {
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    private Category convertToCategory(CategoryDto categoryDto) {
        final Category category = new Category();
        category.setId(categoryDto.getId());
        category.setDescription(categoryDto.getDescription());
        category.setName(categoryDto.getName());
        return category;
    }
}

