package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Role;
import com.mamalimomen.repositories.RoleRepository;
import com.mamalimomen.services.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleRepository> implements RoleService {
    public RoleServiceImpl(RoleRepository baseRepository) {
        super(baseRepository);
    }
}
