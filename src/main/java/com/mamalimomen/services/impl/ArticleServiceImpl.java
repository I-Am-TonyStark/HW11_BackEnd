package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Article;
import com.mamalimomen.repositories.ArticleRepository;
import com.mamalimomen.services.ArticleService;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ArticleServiceImpl extends BaseServiceImpl<Article, Long, ArticleRepository> implements ArticleService {

    public static final Predicate<Article> isPublished = article
            -> article.isPublished();

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        super(articleRepository);
    }

    @Override
    public Optional<Article> findOneArticle(String title) {
        return baseRepository.findOneByNamedQuery("Article.findOneByTitle", title, Article.class);
    }

    @Override
    public List<Article> findAllArticles() {
        return baseRepository.findAllByNamedQuery("Article.findAll", Article.class);
    }

    /*@Override
    public List<Article> findPublishedArticles() {
        return baseRepository.findAllByNamedQuery("Article.findAllWherePublished", Article.class);
    }*/

    @Override
    public List<Article> findPublishedArticles() {
        return baseRepository.findAllByNamedQuery(isPublished, "Article.findAll", Article.class);
    }

    @Override
    public List<Article> findAllUserArticles(Long userID) {
        return baseRepository.findManyByNamedQuery("Article.findAllByWriter", userID, Article.class);
    }
}
