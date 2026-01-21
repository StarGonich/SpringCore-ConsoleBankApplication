package org.example.operations;

import org.example.aop.RequiredArgsCount;
import org.example.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class AccountCloseOperation implements Operation{
    private final AccountService accountService;

    public AccountCloseOperation(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    @RequiredArgsCount(requiredCount = 1,
            errorMessage = "Использование: ACCOUNT_CLOSE [ID счёта]")
    public void execute(String[] args) {
        Long accountId = Long.parseLong(args[0]);
        accountService.accountClose(accountId);
        System.out.println("Счёт успешно закрыт");
    }

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_CLOSE;
    }
}
