package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Long id;
    private final String login;
    private final List<Account> accountList = new ArrayList<>();

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }


    public List<Account> getAccountList() {
        return accountList;
    }

}
