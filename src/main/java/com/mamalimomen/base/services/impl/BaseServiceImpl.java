package com.mamalimomen.base.services.impl;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.base.services.BaseService;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<E extends BaseEntity<PK>, PK extends Number, Repository extends BaseRepository<E, PK>> implements BaseService<E, PK> {
    protected final Repository baseRepository;

    public BaseServiceImpl(Repository baseRepository) {
        this.baseRepository = baseRepository;
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
    public Optional<E> findOneByNamedQuery(String namedQuery, String parameter, Class<E> c) {
        return baseRepository.findOneByNamedQuery(namedQuery, parameter, c);
    }

    @Override
    public List<E> findManyByNamedQuery(String namedQuery, String parameter, Class<E> c) {
        return baseRepository.findManyByNamedQuery(namedQuery, parameter, c);
    }
}
