package org.example.operations;

import org.example.aop.RequiredArgsCount;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserCreateOperation implements Operation {
    private final UserService userService;

    public UserCreateOperation(UserService userService) {
        this.userService = userService;
    }

    @Override
    @RequiredArgsCount(requiredCount = 1, errorMessage = "Использование: USER_CREATE [логин]")
    public void execute(String[] args) {
        User user = userService.createUser(args[0]);
        System.out.printf("Пользователь создан: %s%n", user);
    }

    @Override
    public OperationType getType() {
        return OperationType.USER_CREATE;
    }
}
