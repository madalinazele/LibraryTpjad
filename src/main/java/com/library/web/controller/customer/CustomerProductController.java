package com.library.web.controller.customer;

import com.library.core.utils.PaginatedResult;
import com.library.core.repository.dto.ProductFilterAndSortDto;
import com.library.core.utils.UrlUtils;
import com.library.facade.CategoryFacade;
import com.library.facade.ProductFacade;
import com.library.facade.SearchFacade;
import com.library.facade.dto.ProductCustomerDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Controller
@RequestMapping("/products")
//@Validated
public class CustomerProductController {
    private ProductFacade productFacade;
    private CategoryFacade categoryFacade;
    private SearchFacade searchFacade;

    @GetMapping("/details/{id}")
    public String getProductDetailsPage(@PathVariable String id, Model model) {
        Optional<ProductCustomerDto> productCustomerDto = productFacade.getByIdForCustomer(Integer.parseInt(id));
        if (productCustomerDto.isEmpty()) {
            return "redirect:/products";
        }
        model.addAttribute("product", productCustomerDto.get());
        return "/customer/productDetails";
    }


    @GetMapping
    public String getProductsCustomerPage(ProductFilterAndSortDto productFilterAndSortDto, Model model) {
        PaginatedResult<ProductCustomerDto> paginatedResult =
                this.productFacade.search(productFilterAndSortDto, productFilterAndSortDto.getPage());
        model.addAttribute("processedProducts", paginatedResult.getResultList());
        model.addAttribute("facets", this.searchFacade.getAvailableFacets(productFilterAndSortDto));
        model.addAttribute("currentPage", productFilterAndSortDto.getPage());
        model.addAttribute("noOfPages", paginatedResult.getTotalPages());
        model.addAttribute("currentQuery", UrlUtils.constructUrlFromProductFilterAndSortDto(productFilterAndSortDto));


        return "customer/productsClients";

    }
}



