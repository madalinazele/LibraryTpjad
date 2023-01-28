package com.library.facade.impl;

import com.library.core.model.Category;
import com.library.core.repository.dto.ProductFilterAndSortDto;
import com.library.core.service.CategoryService;
import com.library.core.service.ProductService;
import com.library.core.utils.UrlUtils;
import com.library.core.utils.filter.PriceStatus;
import com.library.core.utils.filter.SortMode;
import com.library.core.utils.filter.StockStatus;
import com.library.facade.SearchFacade;
import com.library.facade.facet.Facet;
import com.library.facade.facet.FacetValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultSearchFacade implements SearchFacade {
    private final CategoryService categoryService;
    private final ProductService productService;

    @Override
    public List<Facet> getAvailableFacets(ProductFilterAndSortDto productFilterAndSortDto) {
        List<Facet> facets = new ArrayList<>();
        facets.add(getCategoryFacet(productFilterAndSortDto));
        facets.add(getPriceFacet(productFilterAndSortDto));
        facets.add(getStockFacet(productFilterAndSortDto));
        facets.add(getSortFacet(productFilterAndSortDto));
        return facets;
    }

    private Facet getSortFacet(ProductFilterAndSortDto productFilterAndSortDto) {
        Facet sortFacet = new Facet();
        sortFacet.setName("Sort");
        sortFacet.setMultiSelect(false);
        sortFacet.setValues(getSortFacetValues(productFilterAndSortDto));
        return sortFacet;
    }

    private List<FacetValue> getSortFacetValues(ProductFilterAndSortDto productFilterAndSortDto) {
        List<FacetValue> facetValues = new ArrayList<>();
        ProductFilterAndSortDto productFilterAndSortDtoSelected = null;
        try {
            productFilterAndSortDtoSelected = (ProductFilterAndSortDto) productFilterAndSortDto.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        for (SortMode mode : SortMode.values()) {
            productFilterAndSortDtoSelected.setSortMode(mode);
            FacetValue facetValue = new FacetValue();
            facetValue.setName(mode.name());
            if (mode == productFilterAndSortDto.getSortMode()) {
                facetValue.setSelected(true);
                productFilterAndSortDtoSelected.setSortMode(null);

            }

            facetValue.setUrl(UrlUtils.constructUrlFromProductFilterAndSortDto(productFilterAndSortDtoSelected));
            facetValues.add(facetValue);
        }
        return facetValues;
    }

    private Facet getStockFacet(ProductFilterAndSortDto productFilterAndSortDto) {
        Facet stockFacet = new Facet();
        stockFacet.setName("Stock");
        stockFacet.setMultiSelect(false);
        stockFacet.setValues(getStockFacetValues(productFilterAndSortDto));

        return stockFacet;
    }

    private List<FacetValue> getStockFacetValues(ProductFilterAndSortDto productFilterAndSortDto) {
        List<FacetValue> facetValues = new ArrayList<>();
        ProductFilterAndSortDto productFilterAndSortDtoSelected = null;
        try {
            productFilterAndSortDtoSelected = (ProductFilterAndSortDto) productFilterAndSortDto.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        for (StockStatus stockStatus : StockStatus.values()) {
            productFilterAndSortDtoSelected.setStock(stockStatus);

            int productCount = productService.count(productFilterAndSortDtoSelected);
            if (productCount == 0) {
                continue;
            }

            FacetValue facetValue = new FacetValue();
            facetValue.setName(stockStatus.getName());
            if (stockStatus == productFilterAndSortDto.getStock()) {
                facetValue.setSelected(true);
                productFilterAndSortDtoSelected.setStock(null);
            }

            facetValue.setCount(productCount);
            facetValue.setUrl(UrlUtils.constructUrlFromProductFilterAndSortDto(productFilterAndSortDtoSelected));
            facetValues.add(facetValue);

        }
        return facetValues;
    }

    private Facet getPriceFacet(ProductFilterAndSortDto productFilterAndSortDto) {
        Facet priceFacet = new Facet();
        priceFacet.setName("Price");
        priceFacet.setMultiSelect(false);
        priceFacet.setValues(getPriceFacetValues(productFilterAndSortDto));

        return priceFacet;
    }

    private List<FacetValue> getPriceFacetValues(ProductFilterAndSortDto productFilterAndSortDto) {
        List<FacetValue> facetValues = new ArrayList<>();
        ProductFilterAndSortDto productFilterAndSortDtoSelected = null;
        try {
            productFilterAndSortDtoSelected = (ProductFilterAndSortDto) productFilterAndSortDto.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        for (PriceStatus priceStatus : PriceStatus.values()) {
            productFilterAndSortDtoSelected.setPrice(priceStatus);
            int productCount = productService.count(productFilterAndSortDtoSelected);
            if (productCount != 0) {
                FacetValue facetValue = new FacetValue();
                facetValue.setName(priceStatus.getName());
                facetValue.setCount(productCount);
                if (priceStatus.equals(productFilterAndSortDto.getPrice())) {
                    facetValue.setSelected(true);
                    productFilterAndSortDtoSelected.setPrice(null);
                }
                facetValue.setUrl(UrlUtils.constructUrlFromProductFilterAndSortDto(productFilterAndSortDtoSelected));
                facetValues.add(facetValue);
            }
        }
        return facetValues;
    }

    private Facet getCategoryFacet(ProductFilterAndSortDto productFilterAndSortDto) {
        Facet categoryFacet = new Facet();
        categoryFacet.setName("Categories");
        categoryFacet.setMultiSelect(true);

        List<Category> categories = categoryService.getAll();
        categoryFacet.setValues(getCategoryFacetValues(productFilterAndSortDto, categories));

        return categoryFacet;
    }

    private List<FacetValue> getCategoryFacetValues(ProductFilterAndSortDto productFilterAndSortDto, List<Category> categories) {
        List<FacetValue> values = new ArrayList<>();
        ProductFilterAndSortDto productFilterAndSortDtoSelected = null;
        try {
            productFilterAndSortDtoSelected = (ProductFilterAndSortDto) productFilterAndSortDto.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        for (Category category : categories) {
            List<Integer> selectedCategories = new ArrayList<>();
            if (productFilterAndSortDto.getCategories() != null) {
                selectedCategories.addAll(productFilterAndSortDto.getCategories());
            }

            FacetValue categoryFacetValue = new FacetValue();
            categoryFacetValue.setName(category.getName());
            if (selectedCategories.contains(category.getId())) {
                categoryFacetValue.setSelected(true);
                selectedCategories.remove(category.getId());
            } else {
                selectedCategories.add(category.getId());
            }

            productFilterAndSortDtoSelected.setCategories(selectedCategories);
            categoryFacetValue.setUrl(UrlUtils.constructUrlFromProductFilterAndSortDto(productFilterAndSortDtoSelected));
            List<Integer> currentCategories = new ArrayList<>();
            currentCategories.add(category.getId());
            productFilterAndSortDtoSelected.setCategories(currentCategories);
            int productCount = productService.count(productFilterAndSortDtoSelected);
            categoryFacetValue.setCount(productCount);
            if (productCount != 0) {
                values.add(categoryFacetValue);
            }
        }
        return values;
    }

}
