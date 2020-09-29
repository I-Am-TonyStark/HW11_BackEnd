package com.mamalimomen.controller.utilities;

import com.mamalimomen.base.controller.utilities.InValidDataException;
import com.mamalimomen.domains.Role;
import com.mamalimomen.services.RoleService;

import java.util.Optional;

public final class SingletonWriterRole {

    private SingletonWriterRole() {
    }

    public static synchronized Role getWriterRole(RoleService roleService) {
        Optional<Role> oRole = roleService.findOneRole("writer");
        if (oRole.isPresent())
            return oRole.get();
        else {
            Role role = new Role();
            try {
                role.setTitle("writer");
                roleService.save(role);
            } catch (InValidDataException e) {
                e.printStackTrace();
            }
            return role;
        }
    }
}
