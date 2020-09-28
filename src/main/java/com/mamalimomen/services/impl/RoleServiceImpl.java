package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Role;
import com.mamalimomen.repositories.RoleRepository;
import com.mamalimomen.services.RoleService;

import java.util.List;
import java.util.Optional;

public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleRepository> implements RoleService {
    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
    }


    @Override
    public Optional<Role> findOneRole(String title) {
        return baseRepository.findOneByNamedQuery("Role.findOneByTitle", title, Role.class);
    }

    @Override
    public List<Role> findAllRoles() {
        return baseRepository.findAllByNamedQuery("Role.findAll", Role.class);
    }
}
