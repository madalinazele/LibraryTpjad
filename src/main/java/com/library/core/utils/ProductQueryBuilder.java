package com.library.core.utils;

import com.library.core.model.Category;
import com.library.core.model.Product;
import com.library.core.utils.filter.Interval;
import com.library.core.utils.filter.SortMode;
import com.library.core.utils.filter.StockStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ProductQueryBuilder {
    private List<String> joinStatements;
    private List<String> whereStatements;
    private String countStatement;

    private final String fromStatement = "FROM " + Product.class.getSimpleName() + " AS p ";
    private List<String> sortStatements;

    public ProductQueryBuilder() {
        joinStatements = new ArrayList<>();
        whereStatements = new ArrayList<>();
        sortStatements = new ArrayList<>();
        countStatement = "";
    }

    public ProductQueryBuilder withCategories(List<Integer> categories) {
        joinStatements.add("JOIN " + Category.class.getSimpleName() + " AS c ON p.category.id = c.id");
        if (categories == null || categories.isEmpty()) {
            return this;
        }

        StringJoiner whereJoiner = new StringJoiner(" OR ", "(", ")");
        categories.forEach(category -> whereJoiner.add("p.category.id = " + category));
        whereStatements.add(whereJoiner.toString());
        return this;
    }

    public ProductQueryBuilder withPrice(Interval interval) {
        if (interval == null || (interval.getLowLimit() == null && interval.getHighLimit() == null)) {
            return this;
        }

        Integer lowLimit = interval.getLowLimit();
        Integer highLimit = interval.getHighLimit();
        if (lowLimit != null && highLimit != null) {
            whereStatements.add("p.price >= " + lowLimit + " AND p.price < " + highLimit);
            return this;
        }

        if (lowLimit != null) {
            whereStatements.add("p.price >= " + lowLimit);
            return this;
        }

        whereStatements.add("p.price < " + highLimit);
        return this;
    }

    public ProductQueryBuilder withStock(StockStatus stock) {
        if (stock == null) {
            return this;
        }

        String whereString = "p.stock ";
        if ("OUT_OF_STOCK".equals(stock.name())) {
            whereString += "<= 0";
        } else {
            whereString += "> 0";
        }

        whereStatements.add(whereString);
        return this;
    }

    public ProductQueryBuilder withCount() {
        countStatement = "COUNT(p.id) ";
        return this;
    }

    public ProductQueryBuilder withSort(SortMode sortMode) {
        if (sortMode == null) {
            sortMode = SortMode.PRICE_ASCENDING;
        }

        String sortString = "p." + sortMode.queryAttribute;
        sortStatements.add(sortString);
        return this;
    }

    public String build() {
        StringBuilder query = new StringBuilder("SELECT ");
        if (!countStatement.isEmpty()) {
            query.append(countStatement);
        } else {
            query.append("p ");
        }

        query.append(fromStatement).append(String.join(" ", joinStatements));
        if (!whereStatements.isEmpty()) {
            query.append(" WHERE " + String.join(" AND ", whereStatements));
        }

        query.append(" ORDER BY " + String.join(", ", sortStatements));

        return query.toString();
    }
}
