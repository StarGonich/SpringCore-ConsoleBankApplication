package org.example.operations;

import org.example.model.Account;
import org.example.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class AccountCreateOperation implements Operation {
    private final AccountService accountService;

    public AccountCreateOperation(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Использование: ACCOUNT_CREATE [ID пользователя]");
        }
        Long userId = Long.parseLong(args[0]);
        Account account = accountService.createAccount(userId);
        System.out.printf("Счёт создан: %s%n", account);
    }

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_CREATE;
    }
}
