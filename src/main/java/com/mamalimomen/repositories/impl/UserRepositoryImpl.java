package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.User;
import com.mamalimomen.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Optional<User> findUserByUserName(String userName) {
        User user;
        try {
            user = em.createNamedQuery("User.findByUserName", User.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public List<User> findAllExceptMe(String userName) {
        return em.createNamedQuery("User.findAllExceptMe", User.class)
                .setParameter("userName", userName)
                .getResultList();
    }

    @Override
    public void delete(Long id) {
        try {
            User user = em.find(User.class, id);
            if (user != null) {
                em.getTransaction().begin();

                user.getArticles().forEach(article -> article.getWriters().remove(user));

                em.remove(user);

                em.getTransaction().commit();
            }
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
    }
}
