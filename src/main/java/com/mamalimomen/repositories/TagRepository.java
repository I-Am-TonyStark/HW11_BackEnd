package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Tag;

import java.util.Optional;

public interface TagRepository extends BaseRepository<Tag, Long> {

    Optional<Tag> findByTitle(String title);

    void delete(Long id);
}
