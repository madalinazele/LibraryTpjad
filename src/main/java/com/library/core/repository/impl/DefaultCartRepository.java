package com.library.core.repository.impl;

import com.library.core.model.Cart;
import com.library.core.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@Transactional
public class DefaultCartRepository extends DefaultAbstractRepository<Cart, Integer> implements CartRepository {

    public DefaultCartRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
