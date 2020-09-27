package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.User;
import com.mamalimomen.repositories.UserRepository;
import com.mamalimomen.services.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Optional<User> findUserByUserName(String userName) {
        return baseRepository.findUserByUserName(userName);
    }

    @Override
    public List<User> findAllExceptMe(String userName) {
        return baseRepository.findAllExceptMe(userName);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(id);
    }
}
