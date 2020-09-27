package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Role;
import com.mamalimomen.repositories.RoleRepository;
import com.mamalimomen.services.RoleService;

import java.util.Optional;

public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Optional<Role> findByTitle(String title) {
        return baseRepository.findByTitle(title);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(id);
    }
}
