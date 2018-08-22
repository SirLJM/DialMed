package com.project.service;

import com.project.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    User getUserById(long userId);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(long userId);
}
