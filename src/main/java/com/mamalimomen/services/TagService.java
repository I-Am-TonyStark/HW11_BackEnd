package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService extends BaseService<Tag, Long> {
    Optional<Tag> findOneTag(String title);

    List<Tag> findAllTags();
}
