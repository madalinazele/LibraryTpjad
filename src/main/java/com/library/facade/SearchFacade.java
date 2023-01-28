package com.library.facade;

import com.library.core.repository.dto.ProductFilterAndSortDto;
import com.library.facade.facet.Facet;

import java.util.List;

public interface SearchFacade {
    List<Facet> getAvailableFacets(ProductFilterAndSortDto productFilterAndSortDto);
}
