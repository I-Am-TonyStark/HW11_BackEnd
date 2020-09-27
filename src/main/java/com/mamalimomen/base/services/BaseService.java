package com.mamalimomen.base.services;

import com.mamalimomen.base.domains.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface BaseService<E extends BaseEntity<PK>, PK extends Number> {
    Optional<E> save(E e);

    Optional<E> update(E e);

    Optional<E> find(E e);

    void delete(E e);

    <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c);

    <R,T> Optional<R> findOneByNamedQuery(Function<E, R> f, String namedQuery, T parameter, Class<E> c);

    <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c);

    List<E> findAllByNamedQuery(String namedQuery, Class<E> c);

    List<E> findAllByNamedQuery(Predicate<E> p, String namedQuery, Class<E> c);

    <R> List<R> findAllByNamedQuery(Function<E, R> f, String namedQuery, Class<E> c);
}
