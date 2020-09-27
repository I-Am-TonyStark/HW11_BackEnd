package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.User;
import com.mamalimomen.domains.UserInfo;
import com.mamalimomen.repositories.UserRepository;
import com.mamalimomen.services.UserService;

import java.util.function.Function;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {
    public static final Function<User, UserInfo> mapUserToUserInfo = user
            -> new UserInfo(
            user.getId()
            , user.getUserName()
            , user.getLastName()
            , user.getRole()
            , user.getAddress().getCity());

    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }
}
