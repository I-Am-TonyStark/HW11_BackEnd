package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Article;
import com.mamalimomen.domains.Category;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends BaseRepository<Article, Long> {
    Optional<Article> findByTitle(String title);

    void delete(Long id);
}
