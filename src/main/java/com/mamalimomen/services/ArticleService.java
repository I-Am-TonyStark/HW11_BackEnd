package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService extends BaseService<Article, Long> {

    Optional<Article> findOneArticle(String title);

    List<Article> findAllArticles();

    List<Article> findPublishedArticles();

    List<Article> findAllUserArticles(Long userID);
}
