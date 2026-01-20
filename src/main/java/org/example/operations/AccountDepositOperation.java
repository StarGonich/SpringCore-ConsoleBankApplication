package org.example.operations;

import org.example.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountDepositOperation implements Operation {
    private final AccountService accountService;

    public AccountDepositOperation(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Использование: ACCOUNT_DEPOSIT [ID счёта] [Сумма]");
        }
        Long accountId = Long.parseLong(args[0]);
        BigDecimal money = new BigDecimal(args[1]);
        accountService.accountDeposit(accountId, money);
        System.out.println("Счёт пополнен");
    }

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_DEPOSIT;
    }
}
