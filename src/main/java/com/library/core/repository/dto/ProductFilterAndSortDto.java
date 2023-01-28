package com.library.core.repository.dto;

import com.library.core.utils.filter.PriceStatus;
import com.library.core.utils.filter.SortMode;
import com.library.core.utils.filter.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductFilterAndSortDto implements Cloneable {
    private List<Integer> categories;
    private PriceStatus price;
    private StockStatus stock;
    private Integer page;
    private SortMode sortMode;

    public ProductFilterAndSortDto() {
        this.page = 1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
