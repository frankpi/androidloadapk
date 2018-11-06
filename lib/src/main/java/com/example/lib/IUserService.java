package com.example.lib;

public interface IUserService {
    String login(String username, String password);

    String logout();
}