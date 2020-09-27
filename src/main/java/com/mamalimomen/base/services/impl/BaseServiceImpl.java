package com.mamalimomen.base.services.impl;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.base.services.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseServiceImpl<E extends BaseEntity<PK>, PK extends Number, Repository extends BaseRepository<E, PK>> implements BaseService<E, PK> {
    protected final Repository baseRepository;

    public BaseServiceImpl(Repository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public void closeEntityManger() {
        baseRepository.closeEntityManger();
    }

    @Override
    public Optional<E> save(E e) {
        return baseRepository.save(e);
    }

    @Override
    public Optional<E> update(E e) {
        return baseRepository.update(e);
    }

    @Override
    public Optional<E> find(E e) {
        return baseRepository.find(e);
    }

    @Override
    public void delete(E e) {
        baseRepository.delete(e);
    }

    @Override
    public <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return baseRepository.findOneByNamedQuery(namedQuery, parameter, c);
    }

    @Override
    public <R, T> Optional<R> findOneByNamedQuery(Function<E, R> f, String namedQuery, T parameter, Class<E> c) {
        return baseRepository.findOneByNamedQuery(f, namedQuery, parameter, c);
    }

    @Override
    public <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return baseRepository.findManyByNamedQuery(namedQuery, parameter, c);
    }

    @Override
    public List<E> findAllByNamedQuery(String namedQuery, Class<E> c) {
        return baseRepository.findAllByNamedQuery(namedQuery, c);
    }

    @Override
    public List<E> findAllByNamedQuery(Predicate<E> p, String namedQuery, Class<E> c) {
        return baseRepository.findAllByNamedQuery(p, namedQuery, c);
    }

    @Override
    public <R> List<R> findAllByNamedQuery(Function<E, R> f, String namedQuery, Class<E> c) {
        return baseRepository.findAllByNamedQuery(f, namedQuery, c);
    }
}
