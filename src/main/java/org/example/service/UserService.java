package org.example.service;

import org.example.model.Account;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private long counter = 0;
    // Две отдельные мапы для быстрого поиска по id и по логину, но увеличение использования памяти
    private Map<Long, User> usersById = new HashMap<>();
    private Map<String, User> usersByLogin = new HashMap<>();

    private final AccountService accountService;

    public UserService(AccountService accountService) {
        this.accountService = accountService;
    }

    public User createUser(String login) {
        usersByLogin.get(login);
        if (usersByLogin.containsKey(login)) {
            System.out.printf("Пользователь с логином %s уже существует.%n", login);
            return null;
        }
        Long userId = ++counter;
        User user = new User(userId, login);
        usersById.put(userId, user);
        usersByLogin.put(login, user);

        accountService.createAccount(userId);
        return user;
    }

    public List<User> getAllUsers() {
        return usersById.values().stream().toList();
    }

    public Map<Long, User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Map<Long, User> usersById) {
        this.usersById = usersById;
    }

    public Map<String, User> getUsersByLogin() {
        return usersByLogin;
    }

    public void setUsersByLogin(Map<String, User> usersByLogin) {
        this.usersByLogin = usersByLogin;
    }
}
