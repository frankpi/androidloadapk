package com.example.testlibrary;

public interface IUserService {
    String login(String username, String password);

    String logout();
}