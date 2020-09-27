package com.mamalimomen.base.services;

import com.mamalimomen.base.domains.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity<PK>, PK extends Number> {
    Optional<E> save(E e);

    Optional<E> update(E e);

    Optional<E> find(E e);

    void delete(E e);

    Optional<E> findOneByNamedQuery(String namedQuery, String parameter, Class<E> c);

    List<E> findManyByNamedQuery(String namedQuery, String parameter, Class<E> c);
}
