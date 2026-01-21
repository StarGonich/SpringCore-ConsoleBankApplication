package org.example.operations;

import org.example.aop.RequiredArgsCount;
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
    @RequiredArgsCount(requiredCount = 2,
            errorMessage = "Использование: ACCOUNT_DEPOSIT [ID счёта] [Сумма]")
    public void execute(String[] args) {
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
