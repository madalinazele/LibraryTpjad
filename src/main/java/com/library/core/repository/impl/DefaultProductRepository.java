package com.library.core.repository.impl;

import com.library.core.model.Product;
import com.library.core.repository.ProductRepository;
import com.library.core.utils.PaginatedResult;
import com.library.core.utils.ProductQueryBuilder;
import com.library.core.utils.filter.Interval;
import com.library.core.repository.dto.ProductFilterAndSortDto;
import com.library.core.utils.filter.PriceStatus;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DefaultProductRepository extends DefaultAbstractRepository<Product, Integer> implements ProductRepository {

    @Value("${pagination.records_per_page}")
    private Integer recordsPerPage;

    public DefaultProductRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> getAllByCategoryId(Integer id) {
        final String queryString = "SELECT p FROM " + Product.class.getSimpleName() + " AS p WHERE p.category.id = :id";
        final Query<Product> query = getSession().createQuery(queryString, Product.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public PaginatedResult<Product> search(ProductFilterAndSortDto queryParamDto, Integer page) {
        Integer totalPages = getTotalPages(queryParamDto);
        PriceStatus priceStatus = queryParamDto.getPrice();
        Interval interval = null;
        if (priceStatus != null) {
            interval = new Interval(priceStatus.getMinPrice(), priceStatus.getMaxPrice());
        }
        final String queryString = new ProductQueryBuilder()
                .withCategories(queryParamDto.getCategories())
                .withPrice(interval)
                .withStock(queryParamDto.getStock())
                .withSort(queryParamDto.getSortMode())
                .build();
        final Query<Product> query = getSession().createQuery(queryString, Product.class);
        query.setMaxResults(recordsPerPage);
        query.setFirstResult((page - 1) * recordsPerPage);
        return getProductPaginatedResult(totalPages, query.getResultList());

    }

    public int count(ProductFilterAndSortDto queryParamDto) {
        PriceStatus priceStatus = queryParamDto.getPrice();
        Interval interval = null;
        if (priceStatus != null) {
            interval = new Interval(priceStatus.getMinPrice(), priceStatus.getMaxPrice());
        }
        final String queryString = new ProductQueryBuilder()
                .withCategories(queryParamDto.getCategories())
                .withPrice(interval)
                .withStock(queryParamDto.getStock())
                .withCount()
                .withSort(queryParamDto.getSortMode())
                .build();
        final Query<Long> query = getSession().createQuery(queryString, Long.class);
        return Math.toIntExact(query.getSingleResult());
    }

    private PaginatedResult<Product> getProductPaginatedResult(Integer totalPages, List<Product> productList) {
        PaginatedResult<Product> paginatedResult = new PaginatedResult<>();
        paginatedResult.setResultList(productList);
        paginatedResult.setRecordsPerPage(recordsPerPage);
        paginatedResult.setTotalPages(totalPages);
        return paginatedResult;
    }

    private Integer getTotalPages(ProductFilterAndSortDto queryParamDto) {
        int numberOfFilteredProducts = count(queryParamDto);

        return numberOfFilteredProducts % recordsPerPage == 0 ?
                Math.toIntExact((numberOfFilteredProducts / recordsPerPage)) :
                Math.toIntExact((numberOfFilteredProducts / recordsPerPage) + 1);

    }


}
