package com.project.service;

import com.project.entity.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public synchronized boolean addUser(User user){
        List<User> list = userRepository.findByLastName(user.getLastName());
        if (!list.isEmpty()) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.delete(getUserById(userId));
    }
}
