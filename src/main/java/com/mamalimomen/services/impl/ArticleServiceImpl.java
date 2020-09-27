package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Article;
import com.mamalimomen.domains.Category;
import com.mamalimomen.repositories.ArticleRepository;
import com.mamalimomen.services.ArticleService;

import java.util.List;
import java.util.Optional;

public class ArticleServiceImpl extends BaseServiceImpl<Article, Long, ArticleRepository> implements ArticleService {
    public ArticleServiceImpl(ArticleRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Optional<Article> findByTitle(String name) {
        return baseRepository.findByTitle(name);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(id);
    }
}
