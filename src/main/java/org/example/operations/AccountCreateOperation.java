package org.example.operations;

import org.example.aop.RequiredArgsCount;
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
    @RequiredArgsCount(requiredCount = 1,
            errorMessage = "Использование: ACCOUNT_CREATE [ID пользователя]")
    public void execute(String[] args) {
        Long userId = Long.parseLong(args[0]);
        Account account = accountService.createAccount(userId);
        System.out.printf("Счёт создан: %s%n", account);
    }

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_CREATE;
    }
}
