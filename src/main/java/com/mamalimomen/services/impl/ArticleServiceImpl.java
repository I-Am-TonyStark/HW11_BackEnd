package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Article;
import com.mamalimomen.repositories.ArticleRepository;
import com.mamalimomen.services.ArticleService;

public class ArticleServiceImpl extends BaseServiceImpl<Article, Long, ArticleRepository> implements ArticleService {
    public ArticleServiceImpl(ArticleRepository baseRepository) {
        super(baseRepository);
    }
}
