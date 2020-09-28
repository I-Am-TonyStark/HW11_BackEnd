package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService extends BaseService<Role, Long> {

    List<Role> findAllRoles();

    Optional<Role> findOneRole(String title);
}
