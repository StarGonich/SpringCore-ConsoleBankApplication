package org.example.operations;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllUsersOperation implements Operation {
    private final UserService userService;

    public ShowAllUsersOperation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(String[] args) {
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
    }

    @Override
    public OperationType getType() {
        return OperationType.SHOW_ALL_USERS;
    }
}
