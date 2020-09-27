package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Article;
import com.mamalimomen.domains.Category;
import com.mamalimomen.domains.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService extends BaseService<Role,Long> {

    Optional<Role> findByTitle(String title);

    void delete(Long id);
}
