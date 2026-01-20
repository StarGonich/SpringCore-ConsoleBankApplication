package org.example.service;

import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private long counter = 0;
    private final Map<Long, User> usersById = new HashMap<>();
    private final Set<String> usersByLogin = new HashSet<>();
    private final AccountService accountService;

    public UserService(AccountService accountService) {
        this.accountService = accountService;
    }

    public User createUser(String login) {
        if (usersByLogin.contains(login)) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует.");
        }
        Long userId = ++counter;
        User user = new User(userId, login);
        usersById.put(userId, user);
        usersByLogin.add(login);

        accountService.createAccount(userId);
        return user;
    }

    public List<User> getAllUsers() {
        return usersById.values().stream().toList();
    }

    public Map<Long, User> getUsersById() {
        return usersById;
    }
}
