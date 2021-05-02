package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.dao.RoleRepository;
import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }


    @Transactional
    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }


//    @Override
//    @Transactional
//    public void updateUser(User user) {
//        userRepository.save(user);
//    }


    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


    @Transactional
    @Override
    public User getUserById(long id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        return null;
    }


    @Transactional
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}


