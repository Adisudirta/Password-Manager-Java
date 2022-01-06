package com.project.passwordmanager.templateObject;

public class Account extends Data{
    public String accountName;
    public String accountId;

    public Account(String accountName, String username, String password, String accountId){
        this.accountName = accountName;
        super.username = username;
        super.password = password;
        this.accountId =accountId;
    }
}
