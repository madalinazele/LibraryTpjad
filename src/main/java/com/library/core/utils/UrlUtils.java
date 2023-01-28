package com.library.core.utils;

import com.library.core.repository.dto.ProductFilterAndSortDto;
import org.apache.commons.collections4.CollectionUtils;

import java.util.StringJoiner;
import java.util.stream.Collectors;

public class UrlUtils {
    public static String constructUrlFromProductFilterAndSortDto(ProductFilterAndSortDto productFilterAndSortDto) {
        StringJoiner queryParamsJoiner = new StringJoiner("&");

        if (CollectionUtils.isNotEmpty(productFilterAndSortDto.getCategories())) {
            String categoriesId = productFilterAndSortDto.getCategories()
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            queryParamsJoiner.add("categories=" + categoriesId);
        }

        if (productFilterAndSortDto.getPrice() != null) {
            queryParamsJoiner.add("price=" + productFilterAndSortDto.getPrice());
        }

        if (productFilterAndSortDto.getStock() != null) {
            queryParamsJoiner.add("stock=" + productFilterAndSortDto.getStock());
        }

        if (productFilterAndSortDto.getSortMode() != null) {
            queryParamsJoiner.add("sortMode=" + productFilterAndSortDto.getSortMode());
        }


        final String queryParams = queryParamsJoiner.toString();

        return queryParams.isEmpty()
                ? "/products"
                : "/products?" + queryParams;
    }
}
