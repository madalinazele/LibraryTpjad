package com.library.facade.impl;

import com.library.core.exception.CategoryNotFoundException;
import com.library.core.exception.InvalidFileException;
import com.library.core.model.Category;
import com.library.core.model.Product;
import com.library.core.repository.dto.ProductFilterAndSortDto;
import com.library.core.service.CategoryService;
import com.library.core.service.ProductService;
import com.library.core.service.utils.FileUploadService;
import com.library.core.utils.FileUtils;
import com.library.core.utils.PaginatedResult;
import com.library.facade.ProductFacade;
import com.library.facade.dto.CategoryDto;
import com.library.facade.dto.ProductCustomerDto;
import com.library.facade.dto.ProductEmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class DefaultProductFacade implements ProductFacade {
    private ProductService productService;
    private FileUploadService fileUploadService;
    private CategoryService categoryService;

    @Override
    public boolean save(ProductEmployeeDto productDto) {
        final Product product = convertProductEmployeeDtoToProduct(productDto);
        MultipartFile imageFile = productDto.getImageFile();
        if (!FileUtils.isImage(imageFile)) {
            throw new InvalidFileException("The selected file is not an image!");
        }
        String imagePath = fileUploadService.saveProductImage(imageFile);
        product.setImagePath(imagePath);
        return productService.save(product);
    }

    @Override
    public boolean update(@Valid ProductEmployeeDto productDto) {
        final Product product = convertProductEmployeeDtoToProduct(productDto);
        MultipartFile imageFile = productDto.getImageFile();
        if (imageFile.isEmpty()) {
            productService.getById(productDto.getId())
                    .ifPresent(currentProduct -> product.setImagePath(currentProduct.getImagePath()));
            return productService.update(product);
        }
        if (!FileUtils.isImage(imageFile)) {
            throw new InvalidFileException("The selected file is not an image!");
        }
        String imagePath = fileUploadService.updateProductImage(imageFile, productDto.getImagePath());
        product.setImagePath(imagePath);
        return productService.update(product);
    }

    @Override
    public boolean delete(Integer id) {
        fileUploadService.removeProductImage(productService.getById(id).get().getImagePath());
        return productService.delete(id);
    }

    @Override
    public Optional<ProductEmployeeDto> getById(Integer id) {
        return productService.getById(id).map(this::convertProductToProductEmployeeDto);
    }

    @Override
    public Optional<ProductCustomerDto> getByIdForCustomer(Integer id) {
        return productService.getById(id).map(this::convertProductToProductCustomerDto);
    }

    @Override
    public Optional<ProductEmployeeDto> getByIdForEmployee(Integer id) {
        return productService.getById(id).map(this::convertProductToProductEmployeeDto);
    }

    @Override
    @Transactional
    public List<ProductEmployeeDto> getAll() {
        return productService.getAll()
                .stream()
                .map(this::convertProductToProductEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedResult<ProductCustomerDto> search(ProductFilterAndSortDto productFilterAndSortDto, Integer page) {

        PaginatedResult<ProductCustomerDto> productCustomerDtoPaginatedResult = new PaginatedResult<>();
        PaginatedResult<Product> productPaginatedResult = productService.search(productFilterAndSortDto, page);

        productCustomerDtoPaginatedResult.setResultList(productPaginatedResult
                .getResultList()
                .stream()
                .map(this::convertProductToProductCustomerDto)
                .collect(Collectors.toList()));
        productCustomerDtoPaginatedResult.setTotalPages(productPaginatedResult.getTotalPages());
        productCustomerDtoPaginatedResult.setRecordsPerPage(productPaginatedResult.getRecordsPerPage());
        return productCustomerDtoPaginatedResult;

    }

    private ProductEmployeeDto convertProductToProductEmployeeDto(Product product) {
        ProductEmployeeDto productEmployeeDto = new ProductEmployeeDto();
        productEmployeeDto.setId(product.getId());
        productEmployeeDto.setName(product.getName());
        productEmployeeDto.setDescription(product.getDescription());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setName(product.getCategory().getName());
        categoryDto.setDescription(product.getCategory().getDescription());
        productEmployeeDto.setCategory(categoryDto);
        productEmployeeDto.setPrice(product.getPrice());
        productEmployeeDto.setImagePath("/images/" + product.getImagePath());
        productEmployeeDto.setStock(product.getStock());
        productEmployeeDto.setManufacturerPartNumber(product.getManufacturerPartNumber());
        return productEmployeeDto;
    }

    private Product convertProductEmployeeDtoToProduct(ProductEmployeeDto productEmployeeDto) {
        Integer categoryId = productEmployeeDto.getCategory().getId();
        Category category = categoryService.getById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Product product = new Product();
        product.setId(productEmployeeDto.getId());
        product.setName(productEmployeeDto.getName());
        product.setDescription(productEmployeeDto.getDescription());
        product.setCategory(category);
        product.setPrice(productEmployeeDto.getPrice());
        product.setImagePath(productEmployeeDto.getImagePath());
        product.setStock(productEmployeeDto.getStock());
        product.setManufacturerPartNumber(productEmployeeDto.getManufacturerPartNumber());
        return product;
    }

    private ProductCustomerDto convertProductToProductCustomerDto(Product product) {
        ProductCustomerDto productCustomerDto = new ProductCustomerDto();
        productCustomerDto.setId(product.getId());
        productCustomerDto.setName(product.getName());
        productCustomerDto.setDescription(product.getDescription());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setName(product.getCategory().getName());
        categoryDto.setDescription(product.getCategory().getDescription());
        productCustomerDto.setCategory(categoryDto);
        productCustomerDto.setPrice(product.getPrice());
        productCustomerDto.setImagePath("/images/" + product.getImagePath());
        productCustomerDto.setStock(product.getStock());
        productCustomerDto.setManufacturerPartNumber(product.getManufacturerPartNumber());
        return productCustomerDto;
    }
}