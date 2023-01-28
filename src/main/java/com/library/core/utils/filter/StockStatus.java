package com.library.core.utils.filter;

import lombok.Getter;

@Getter
public enum StockStatus {
    OUT_OF_STOCK("out of stock"),
    IN_STOCK("in stock");
    private String name;

    StockStatus(String name){
        this.name = name;
    }
}
