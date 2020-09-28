package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Category;
import com.mamalimomen.repositories.CategoryRepository;
import com.mamalimomen.services.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl extends BaseServiceImpl<Category, Long, CategoryRepository> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

    @Override
    public Optional<Category> findOneCategory(String title) {
        return baseRepository.findOneByNamedQuery("Category.findOneByTitle", title, Category.class);
    }

    @Override
    public List<Category> findAllCategories() {
        return baseRepository.findAllByNamedQuery("Category.findAll", Category.class);
    }
}
