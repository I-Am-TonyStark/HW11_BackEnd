package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Article;
import com.mamalimomen.repositories.ArticleRepository;

import javax.persistence.EntityManager;

public class ArticleRepositoryImpl extends BaseRepositoryImpl<Article, Long> implements ArticleRepository {
    public ArticleRepositoryImpl(EntityManager em) {
        super(em);
    }
}
