package com.example.demo.service.Impl;

import com.example.demo.helper.UserException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserException("User with email " + user.getEmail() + " already exists");
        } else {
            user.setUserId(Generators.timeBasedGenerator().generate().toString());
            user.setRole("USER");
            return userRepository.save(user);
        }
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

