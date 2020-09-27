package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Article;
import com.mamalimomen.domains.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends BaseService<Category, Long> {

    Optional<Category> findByTitle(String title);

    void delete(Long id);
}
