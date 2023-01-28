package com.library.web.controller.employee;

import com.library.core.exception.InvalidFileException;
import com.library.core.repository.dto.ProductFilterAndSortDto;
import com.library.core.utils.PaginatedResult;
import com.library.facade.CategoryFacade;
import com.library.facade.ProductFacade;
import com.library.facade.dto.ProductCustomerDto;
import com.library.facade.dto.ProductEmployeeDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Controller
@RequestMapping("/admin/products")
public class EmployeeProductController {

    private final ProductFacade productFacade;
    private final CategoryFacade categoryFacade;

    @GetMapping
    public String getProductsPage(ProductFilterAndSortDto productFilterAndSortDto, Model model) {
        PaginatedResult<ProductCustomerDto> paginatedResult =
                this.productFacade.search(productFilterAndSortDto, productFilterAndSortDto.getPage());

        model.addAttribute("processedProducts", paginatedResult.getResultList());
        model.addAttribute("currentPage", productFilterAndSortDto.getPage());
        model.addAttribute("noOfPages", paginatedResult.getTotalPages());
        return "employee/productsEmployee";
    }

    @GetMapping("/")
    public String getView() {
        return "employee/addProduct";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new ProductEmployeeDto());
        }
        model.addAttribute("categories", categoryFacade.getAll());
        return "employee/addProduct";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute("product") ProductEmployeeDto product, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            productFacade.save(product);
        } catch (InvalidFileException invalidFileException) {
            redirectAttributes.addFlashAttribute("errorMessage", invalidFileException.getMessage());
            redirectAttributes.addFlashAttribute("product", product);
            return "redirect:/admin/products/add";
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productFacade.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateProductView(@PathVariable Integer id, Model model) {
        final Optional<ProductEmployeeDto> productDto = productFacade.getById(id);
        if (productDto.isEmpty()) {
            return "redirect:/admin/products";
        }
        productDto.ifPresent(product -> model.addAttribute("product", product));
        model.addAttribute("categories", categoryFacade.getAll());
        return "employee/updateProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") ProductEmployeeDto product, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            productFacade.update(product);
        } catch (InvalidFileException invalidFileException) {
            redirectAttributes.addFlashAttribute("errorMessage", invalidFileException.getMessage());
            String updateId = product.getId().toString();
            return "redirect:/admin/products/update/" + updateId;
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/details/{id}")
    public String getProductEmployeeDetailsPage(@PathVariable String id, Model model) {
        Optional<ProductEmployeeDto> productEmployeeDto = productFacade.getByIdForEmployee(Integer.parseInt(id));
        if (productEmployeeDto.isEmpty()) {
            return "redirect:/admin/products";
        }
        productEmployeeDto.ifPresent(product -> model.addAttribute("product", productEmployeeDto.get()));
        return "employee/productDetails";
    }
}
