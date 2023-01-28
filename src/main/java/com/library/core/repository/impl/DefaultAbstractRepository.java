package com.library.core.repository.impl;

import com.library.core.repository.AbstractRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
public class DefaultAbstractRepository<ENTITY, PK extends Serializable> implements AbstractRepository<ENTITY, PK> {

    private final Class<ENTITY> persistentClass;
    private final SessionFactory sessionFactory;

    public DefaultAbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<ENTITY>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<ENTITY> findAll() {
        final Query<ENTITY> query = getSession().createQuery("FROM " + persistentClass.getSimpleName(), persistentClass);
        return query.getResultList();
    }

    @Override
    public Optional<ENTITY> findById(PK id) {
        try {
            return Optional.ofNullable(getSession().get(persistentClass, id));
        } catch (HibernateException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean save(ENTITY entity) {
        try {
            getSession().save(entity);
        } catch (HibernateException hibernateException) {
            log.error(hibernateException.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(ENTITY entity) {
        try {
            getSession().delete(entity);
        } catch (HibernateException hibernateException) {
            log.error(hibernateException.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(PK id) {
        final Optional<ENTITY> entity = findById(id);
        if (entity.isEmpty()) {
            return false;
        }
        return delete(entity.get());
    }

    @Override
    public boolean update(ENTITY entity) {
        try {
            getSession().update(entity);
        } catch (HibernateException hibernateException) {
            log.error(hibernateException.getMessage());
            return false;
        }
        return true;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

