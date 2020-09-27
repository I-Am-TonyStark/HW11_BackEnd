package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Category;
import com.mamalimomen.repositories.CategoryRepository;
import com.mamalimomen.services.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl<Category, Long, CategoryRepository> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository badeRepository) {
        super(badeRepository);
    }
}
