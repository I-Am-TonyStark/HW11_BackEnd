package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.User;
import com.mamalimomen.repositories.UserRepository;
import com.mamalimomen.services.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }
}
