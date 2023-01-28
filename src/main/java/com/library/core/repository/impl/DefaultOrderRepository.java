package com.library.core.repository.impl;

import com.library.core.model.Order;
import com.library.core.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@Transactional
public class DefaultOrderRepository extends DefaultAbstractRepository<Order, Integer> implements OrderRepository {

    public DefaultOrderRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
