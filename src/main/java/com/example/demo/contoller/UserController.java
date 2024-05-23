package com.example.demo.contoller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test")
    public String test() {
        return "Working Fine";
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping("/email")
    public User getUserByEmail(@RequestBody String email) {
        return userService.getUser(email);
    }

    @GetMapping("/{Id}")
    public User getUserById(@PathVariable("Id") String Id) {
        return userService.getUserById(Id);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getUserCount() {
        Integer count = userService.getUsers().size();
        return ResponseEntity.ok(count);
    }
}

