package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Article;
import com.mamalimomen.domains.Category;
import com.mamalimomen.domains.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService extends BaseService<Tag, Long> {

    Optional<Tag> findByTitle(String title);

    void delete(Long id);
}
