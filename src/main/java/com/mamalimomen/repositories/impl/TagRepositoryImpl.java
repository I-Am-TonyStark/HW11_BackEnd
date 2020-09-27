package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Tag;
import com.mamalimomen.repositories.TagRepository;

import javax.persistence.EntityManager;

public class TagRepositoryImpl extends BaseRepositoryImpl<Tag, Long> implements TagRepository {
    public TagRepositoryImpl(EntityManager em) {
        super(em);
    }
}
