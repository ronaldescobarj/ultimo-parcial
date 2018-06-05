package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.User;

public interface UserService {
    void save(User user);

    void save2(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Integer id);

    void deleteUser(Integer id);

}