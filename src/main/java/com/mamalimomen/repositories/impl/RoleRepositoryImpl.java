package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Role;
import com.mamalimomen.repositories.RoleRepository;

import javax.persistence.EntityManager;

public class RoleRepositoryImpl extends BaseRepositoryImpl<Role, Long> implements RoleRepository {
    public RoleRepositoryImpl(EntityManager em) {
        super(em);
    }
}
