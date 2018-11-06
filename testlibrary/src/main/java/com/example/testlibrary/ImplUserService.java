package com.example.testlibrary;

import com.example.lib.IUserService;

public class ImplUserService implements IUserService {
    @Override
    public String login(String s, String s1) {
        return "loadapk login";
    }

    @Override
    public String logout() {
        return "loadapk logout";
    }
}
