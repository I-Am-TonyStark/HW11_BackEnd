package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Article;
import com.mamalimomen.domains.Category;
import com.mamalimomen.domains.Tag;
import com.mamalimomen.domains.User;

import java.util.List;
import java.util.Optional;

public interface ArticleService extends BaseService<Article, Long> {

    Optional<Article> findByTitle(String title);

    void delete(Long id);
}
