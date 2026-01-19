package org.example.service;

import org.example.model.Account;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class OperationsConsoleListener {
    private final UserService userService;
    private final AccountService accountService;

    public OperationsConsoleListener(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    public void openConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Банковское приложение. Для выхода введите 'exit'");
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы...");
                break;
            }
            processCommand(command);
        }
        scanner.close();
    }

    private void processCommand(String commandInput) {
        if (commandInput.isEmpty()) return;
        String[] parts = commandInput.split("\\s+");

        String command = parts[0].toUpperCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        try {
            switch (command) {
                case "USER_CREATE": {
                    if (args.length != 1) {
                        System.out.println("Использование: USER_CREATE [логин]");
                        break;
                    }
                    User user = userService.createUser(args[0]);
                    System.out.printf("Пользователь создан: %s%n", user);
                    break;
                }
                case "SHOW_ALL_USERS": {
                    List<User> users = userService.getAllUsers();
                    for (User user : users) System.out.println(user);
                    break;
                }
                case "ACCOUNT_CREATE":
                    if (args.length != 1) {
                        System.out.println("Использование: ACCOUNT_CREATE [ID пользователя]");
                        break;
                    }
                    Long userId = Long.parseLong(args[0]);
                    Account account = accountService.createAccount(userId);
                    System.out.printf("Счёт создан: %s%n", account);
                    break;
                case "ACCOUNT_CLOSE": {
                    if (args.length != 1) {
                        System.out.println("Использование: ACCOUNT_CLOSE [ID счёта]");
                        break;
                    }
                    Long accountId = Long.parseLong(args[0]);
                    int status = accountService.accountClose(accountId);
                    switch (status) {
                        case 0:
                            System.out.println("Счёт успешно закрыт");
                            break;
                        case -1:
                            System.out.println("Невозможно закрыть единственный счёт пользователя");
                            break;
                    }
                    break;
                }
                case "ACCOUNT_DEPOSIT": {
                    if (args.length != 2) {
                        System.out.println("Использование: ACCOUNT_DEPOSIT [ID счёта] [Сумма]");
                        break;
                    }
                    Long accountId = Long.parseLong(args[0]);
                    BigDecimal money = new BigDecimal(args[1]);
                    accountService.accountDeposit(accountId, money);
                    break;
                }
                case "ACCOUNT_TRANSFER": {
                    if (args.length != 3) {
                        System.out.println("Использование: ACCOUNT_DEPOSIT [ID счёта откуда] [ID счёта куда] [Сумма]");
                        break;
                    }
                    Long accountId1 = Long.parseLong(args[0]);
                    Long accountId2 = Long.parseLong(args[1]);
                    BigDecimal money = new BigDecimal(args[2]);
                    accountService.accountTransfer(accountId1, accountId2, money);
                    break;
                }
                case "ACCOUNT_WITHDRAW": {
                    if (args.length != 2) {
                        System.out.println("Использование: ACCOUNT_DEPOSIT [ID счёта] [Сумма]");
                        break;
                    }
                    Long accountId = Long.parseLong(args[0]);
                    BigDecimal money = new BigDecimal(args[1]);
                    accountService.accountWithdraw(accountId, money);
                    break;
                }
                default:
                    System.out.println("Неизвестная команда: " + command);
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении команды: " + e.getMessage());
        }
    }
}
