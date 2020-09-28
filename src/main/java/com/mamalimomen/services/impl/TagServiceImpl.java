package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Tag;
import com.mamalimomen.repositories.TagRepository;
import com.mamalimomen.services.TagService;

import java.util.List;
import java.util.Optional;

public class TagServiceImpl extends BaseServiceImpl<Tag, Long, TagRepository> implements TagService {
    public TagServiceImpl(TagRepository tagRepository) {
        super(tagRepository);
    }

    @Override
    public Optional<Tag> findOneTag(String title) {
        return baseRepository.findOneByNamedQuery("Tag.findOneByTitle", title, Tag.class);
    }

    @Override
    public List<Tag> findAllTags() {
        return baseRepository.findAllByNamedQuery("Tag.findAll", Tag.class);
    }
}
