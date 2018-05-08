package com.ucbcba.demo.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}