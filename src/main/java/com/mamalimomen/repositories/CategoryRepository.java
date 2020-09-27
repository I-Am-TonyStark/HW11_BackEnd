package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Category;

import java.util.Optional;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

    void delete(Long id);
}
