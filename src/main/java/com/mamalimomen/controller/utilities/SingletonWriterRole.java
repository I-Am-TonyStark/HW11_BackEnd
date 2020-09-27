package com.mamalimomen.controller.utilities;

import com.mamalimomen.domains.Role;
import com.mamalimomen.services.RoleService;

import java.util.Optional;

public final class SingletonWriterRole {

    private SingletonWriterRole() {
    }

    public static synchronized Role getWriterRole(RoleService roleService) {
        Optional<Role> oRole = roleService.findByTitle("writer");
        if (oRole.isPresent())
            return oRole.get();
        else {
            Role role = new Role("writer");
            return role;
        }
    }
}
