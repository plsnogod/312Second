package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> showAllUsers();
    void deleteUser(long id);
    User getUserByEmail(String email);
    User getUserById(long id);
    User getUserByName(String name);
}

