package com.library.core.utils.filter;

public enum SortMode {
    PRICE_ASCENDING("price ASC"),
    PRICE_DESCENDING("price DESC"),
    NAME_ASCENDING("name ASC"),
    NAME_DESCENDING("name DESC");

    public final String queryAttribute;

    SortMode(String query) {
        this.queryAttribute = query;
    }
}
