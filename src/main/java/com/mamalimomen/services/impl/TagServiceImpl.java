package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Tag;
import com.mamalimomen.repositories.TagRepository;
import com.mamalimomen.services.TagService;

public class TagServiceImpl extends BaseServiceImpl<Tag, Long, TagRepository> implements TagService {
    public TagServiceImpl(TagRepository baseRepository) {
        super(baseRepository);
    }
}
