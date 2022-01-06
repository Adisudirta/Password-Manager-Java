package com.project.passwordmanager.templateObject;

import java.util.UUID;

public class User extends Data{
    public String userId;

    public User(String username, String password, String userId){
        super.username = username;
        super.password = password;
        this.userId = userId;
    }
}
