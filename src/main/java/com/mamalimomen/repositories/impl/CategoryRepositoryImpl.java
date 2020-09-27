package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Category;
import com.mamalimomen.repositories.CategoryRepository;

import javax.persistence.EntityManager;

public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category, Long> implements CategoryRepository {
    public CategoryRepositoryImpl(EntityManager em) {
        super(em);
    }
}
