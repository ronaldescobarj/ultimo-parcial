package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.User;

public interface UserService {

    Iterable<User> listAllUsers();

    void saveUser(User user);

    User getUser(Integer id);

    void deleteUser(Integer id);
    
}