package org.example.operations;

import org.example.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class AccountCloseOperation implements Operation{
    private final AccountService accountService;

    public AccountCloseOperation(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Использование: ACCOUNT_CLOSE [ID счёта]");
        }
        Long accountId = Long.parseLong(args[0]);
        accountService.accountClose(accountId);
        System.out.println("Счёт успешно закрыт");
    }

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_CLOSE;
    }
}
