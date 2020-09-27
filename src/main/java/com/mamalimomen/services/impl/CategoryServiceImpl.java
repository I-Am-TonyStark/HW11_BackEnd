package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Category;
import com.mamalimomen.repositories.CategoryRepository;
import com.mamalimomen.services.CategoryService;

import java.util.Optional;

public class CategoryServiceImpl extends BaseServiceImpl<Category, Long, CategoryRepository> implements CategoryService {

    public CategoryServiceImpl(CategoryRepository badeRepository) {
        super(badeRepository);
    }

    @Override
    public Optional<Category> findByTitle(String title) {
        return baseRepository.findByTitle(title);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(id);
    }
}
