package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends BaseService<Category, Long> {

    Optional<Category> findOneCategory(String title);

    List<Category> findAllCategories();
}
