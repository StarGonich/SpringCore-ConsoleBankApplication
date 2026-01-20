package org.example.service;

import org.example.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
public class AccountService {
    private final Map<Long, Account> accountsById = new HashMap<>();
    private long counter = 0L;
    private final BigDecimal defaultAmount;
    private final BigDecimal transferCommission;
    private final UserService userService;

    public AccountService(@Value("${account.default-amount}") String defaultAmount,
                          @Value("${account.transfer-commission}") String transferCommission,
                          @Lazy UserService userService) {
        this.defaultAmount = new BigDecimal(defaultAmount).setScale(2, RoundingMode.HALF_EVEN);
        this.transferCommission = new BigDecimal(transferCommission);
        this.userService = userService;
    }

    public Account createAccount(Long userId) {
        Long accountId = ++counter;
        Account account = new Account(accountId, userId, defaultAmount);
        accountsById.put(accountId, account);
        userService.getUsersById().get(userId).getAccountList().add(account);
        return account;
    }

    public void accountClose(Long accountId) {
        Account account = accountsById.get(accountId);
        Long userId = account.getUserId();
        List<Account> accounts = new ArrayList<>();
        for (Account acc : accountsById.values()) {
            if (acc.getUserId().equals(userId)) accounts.add(acc);
        }
        if (accounts.size() == 1) {
            throw new IllegalArgumentException("Нельзя закрыть единственный счёт пользователя");
        }
        accountsById.remove(accountId);
        userService.getUsersById().get(account.getUserId()).getAccountList().remove(account);
        accounts.remove(account);
        BigDecimal money = account.getAmount();
        Account firstAccount = accounts.getFirst();
        firstAccount.setAmount(firstAccount.getAmount().add(money));
    }

    public void accountDeposit(Long accountId, BigDecimal money) {
        Account account = accountsById.get(accountId);
        account.setAmount(account.getAmount().add(money));
    }

    public void accountTransfer(Long accountId1, Long accountId2, BigDecimal money) {
        Account account1 = accountsById.get(accountId1);
        Account account2 = accountsById.get(accountId2);
        account1.setAmount(account1.getAmount().subtract(money));
        if (account1.getUserId().equals(account2.getUserId())) {
            account2.setAmount(account2.getAmount().add(money));
        } else {
            account2.setAmount(account2.getAmount().add(money.multiply(
                    BigDecimal.ONE.subtract(transferCommission)
            )));
        }
    }

    public void accountWithdraw(Long accountId, BigDecimal money) {
        Account account = accountsById.get(accountId);
        account.setAmount(account.getAmount().subtract(money));
    }
}
