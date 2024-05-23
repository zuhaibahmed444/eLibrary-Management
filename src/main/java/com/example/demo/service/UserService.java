package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws Exception;

    User getUser(String email);

    User updateUser(User user);

    List<User> getUsers();

    User getUserById(String userId);
}

