package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.User;
import com.mamalimomen.domains.UserInfo;
import com.mamalimomen.repositories.UserRepository;
import com.mamalimomen.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {
    public static final Function<User, UserInfo> mapUserToUserInfo = user
            -> new UserInfo(
            user.getId()
            , user.getUserName()
            , user.getLastName()
            , user.getRole()
            , user.getAddress().getCity());

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public Optional<User> findOneUser(String userName) {
        return baseRepository.findOneByNamedQuery("User.findOneByUserName", userName, User.class);
    }

    @Override
    public Optional<User> findOneUser(Long userID) {
        return baseRepository.findOneByNamedQuery("User.findOneByUserID", userID, User.class);
    }

    @Override
    public Optional<UserInfo> findOneUserInfo(String userName) {
        return baseRepository.findOneByNamedQuery(mapUserToUserInfo, "User.findOneByUserName", userName, User.class);
    }

    @Override
    public List<User> findAllExceptMe(String myUserName) {
        return baseRepository.findManyByNamedQuery("User.findAllWhereNotMe", myUserName, User.class);
    }

    @Override
    public List<UserInfo> findAllExceptMeInfo(String myUserName) {
        return baseRepository.findManyByNamedQuery(mapUserToUserInfo, "User.findAllWhereNotMe", myUserName, User.class);
    }

    @Override
    public List<User> findAllUsers() {
        return baseRepository.findAllByNamedQuery("User.findAll", User.class);
    }

    @Override
    public List<UserInfo> findAllUsersInfo() {
        return baseRepository.findAllByNamedQuery(mapUserToUserInfo, "User.findAll", User.class);
    }
}
