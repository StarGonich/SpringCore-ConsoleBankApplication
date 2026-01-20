package org.example.operations;

import org.example.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountTransferOperation implements Operation {
    private final AccountService accountService;

    public AccountTransferOperation(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Использование: ACCOUNT_DEPOSIT [ID счёта откуда] [ID счёта куда] [Сумма]");
        }
        Long accountId1 = Long.parseLong(args[0]);
        Long accountId2 = Long.parseLong(args[1]);
        BigDecimal money = new BigDecimal(args[2]);
        accountService.accountTransfer(accountId1, accountId2, money);
        System.out.println("Перевод выполнен");
    }

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_TRANSFER;
    }
}
