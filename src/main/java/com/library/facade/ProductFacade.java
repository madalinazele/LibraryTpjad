package com.library.facade;

import com.library.core.utils.PaginatedResult;
import com.library.core.repository.dto.ProductFilterAndSortDto;
import com.library.facade.dto.ProductCustomerDto;
import com.library.facade.dto.ProductEmployeeDto;

import java.util.List;
import java.util.Optional;

public interface ProductFacade {

    boolean save(ProductEmployeeDto productDto);

    boolean update(ProductEmployeeDto productDto);

    boolean delete(Integer id);

    Optional<ProductEmployeeDto> getById(Integer id);

    Optional<ProductCustomerDto> getByIdForCustomer(Integer id);

    Optional<ProductEmployeeDto> getByIdForEmployee(Integer id);

    List<ProductEmployeeDto> getAll();

    PaginatedResult<ProductCustomerDto> search(ProductFilterAndSortDto productFilterAndSortDto, Integer page);

}
