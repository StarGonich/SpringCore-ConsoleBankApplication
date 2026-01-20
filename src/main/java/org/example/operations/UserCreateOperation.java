package org.example.operations;

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
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Использование: USER_CREATE [логин]");
        }
        User user = userService.createUser(args[0]);
        System.out.printf("Пользователь создан: %s%n", user);
    }

    @Override
    public OperationType getType() {
        return OperationType.USER_CREATE;
    }
}
