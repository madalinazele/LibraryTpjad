package com.library.core.repository.impl;

import com.library.core.model.Category;
import com.library.core.repository.CategoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultCategoryRepository extends DefaultAbstractRepository<Category, Integer> implements CategoryRepository {

    public DefaultCategoryRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
