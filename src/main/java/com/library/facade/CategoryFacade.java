package com.library.facade;

import com.library.facade.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryFacade {

    List<CategoryDto> getAll();

    Optional<CategoryDto> getById(Integer id);

    boolean save(CategoryDto categoryDto);

    boolean update(CategoryDto newCategory);

    boolean delete(Integer id);
}
