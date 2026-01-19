package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Long id;
    private String login;
    // Что лучше: List или HashMap
    private List<Account> accountList = new ArrayList<>();

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
