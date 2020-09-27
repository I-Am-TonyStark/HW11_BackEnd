package com.mamalimomen.base.repositories;

import com.mamalimomen.base.domains.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends BaseEntity<PK>, PK extends Number> {
    Optional<E> save(E e);

    Optional<E> update(E e);

    Optional<E> find(E e);

    void delete(E e);

    Optional<E> findOneByNamedQuery(String namedQuery, String parameter, Class<E> c);

    List<E> findManyByNamedQuery(String namedQuery, String parameter, Class<E> c);
}
