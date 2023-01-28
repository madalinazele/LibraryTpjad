package com.library.core.repository.impl;

import com.library.core.model.CartEntry;
import com.library.core.repository.CartEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Slf4j
@Transactional
public class DefaultCartEntryRepository extends DefaultAbstractRepository<CartEntry, Integer> implements CartEntryRepository {

    public DefaultCartEntryRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
