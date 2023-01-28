package com.library.core.utils.filter;
import lombok.Getter;

@Getter
public enum PriceStatus {
        between0and100(0, 100, "0-100"),
        between100and200(100, 200, "100-200"),
        between200and300(200, 300, "200-300"),
        over300(300, "300+");

    private Integer minPrice;
    private Integer maxPrice;
    private String name;

    PriceStatus(Integer minPrice, Integer maxPrice, String name) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.name = name;
    }

    PriceStatus(Integer minPrice, String name) {
        this.minPrice = minPrice;
        this.name = name;
    }

}


