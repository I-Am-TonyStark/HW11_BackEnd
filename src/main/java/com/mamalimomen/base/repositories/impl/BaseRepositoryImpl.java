package com.mamalimomen.base.repositories.impl;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Article;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseRepositoryImpl<E extends BaseEntity<PK>, PK extends Number> implements BaseRepository<E, PK> {
    protected final EntityManager em;

    public BaseRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<E> save(E e) {
        try {
            em.getTransaction().begin();

            em.persist(e);

            em.getTransaction().commit();

            return Optional.of(e);
        } catch (EntityExistsException exception) {
            em.getTransaction().rollback();
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> update(E e) {
        try {
            em.getTransaction().begin();

            e = em.merge(e);

            em.getTransaction().commit();

            return Optional.of(e);
        } catch (IllegalArgumentException exception) {
            em.getTransaction().rollback();
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> find(E e) {
        E found = (E) em.find(e.getClass(), e.getId());
        return e != null ? Optional.of(e) : Optional.empty();
    }

    @Override
    public void delete(E e) {
        try {

            Optional<E> optionalE = find(e);
            if (optionalE.isPresent()) {
                em.getTransaction().begin();

                em.remove(e);

                em.getTransaction().commit();
            }
        } catch (IllegalArgumentException exception) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public <T> Optional<E> findOneByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        E e;
        try {
            e = em.createNamedQuery(namedQuery, c)
                    .setParameter(1, parameter)
                    .getSingleResult();
        } catch (NoResultException exception) {
            e = null;
        }
        return e != null ? Optional.of(e) : Optional.empty();
    }

    @Override
    public <R, T> Optional<R> findOneByNamedQuery(Function<E, R> f, String namedQuery, T parameter, Class<E> c) {
        E e;
        try {
            e = em.createNamedQuery(namedQuery, c)
                    .setParameter(1, parameter)
                    .getSingleResult();
        } catch (NoResultException exception) {
            e = null;
        }
        return e != null ? Optional.of(f.apply(e)) : Optional.empty();
    }

    @Override
    public <T> List<E> findManyByNamedQuery(String namedQuery, T parameter, Class<E> c) {
        return em.createNamedQuery(namedQuery, c)
                .setParameter(1, parameter)
                .getResultList();
    }

    @Override
    public List<E> findAllByNamedQuery(String namedQuery, Class<E> c) {
        return em.createNamedQuery(namedQuery, c)
                .getResultList();
    }

    @Override
    public List<E> findAllByNamedQuery(Predicate<E> p, String namedQuery, Class<E> c) {
        return findAllByNamedQuery(namedQuery, c)
                .stream()
                .filter(p)
                .collect(Collectors.toList());
    }

    @Override
    public <R> List<R> findAllByNamedQuery(Function<E, R> f, String namedQuery, Class<E> c) {
        return findAllByNamedQuery(namedQuery, c)
                .stream()
                .map(f)
                .collect(Collectors.toList());
    }
}
