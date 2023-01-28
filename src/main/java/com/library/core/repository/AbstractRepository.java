package com.library.core.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AbstractRepository<ENTITY, PK extends Serializable> {

    List<ENTITY> findAll();

    Optional<ENTITY> findById(PK pk);

    boolean save(ENTITY entity);

    boolean delete(ENTITY entity);

    boolean deleteById(PK id);

    boolean update(ENTITY entity);
}
