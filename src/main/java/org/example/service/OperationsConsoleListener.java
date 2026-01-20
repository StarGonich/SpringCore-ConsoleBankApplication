package org.example.service;

import org.example.operations.Operation;
import org.example.operations.OperationType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class OperationsConsoleListener {
    private final Map<OperationType, Operation> operationMap;

    public OperationsConsoleListener(List<Operation> operations) {
        this.operationMap = operations.stream()
                .collect(Collectors.toMap(Operation::getType, op -> op));
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
            if (command.isEmpty()) continue;
            processCommand(command);
        }
        scanner.close();
    }

    private void processCommand(String commandInput) {
        String[] parts = commandInput.split("\\s+");

        String command = parts[0].toUpperCase();
        OperationType operation;
        try {
            operation = OperationType.valueOf(command);
        } catch (IllegalArgumentException e) {
            System.out.println("Неизвестная команда: " + command);
            return;
        }
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        try {
            operationMap.get(operation).execute(args);
        } catch (Exception e) {
            System.out.println("Произошла ошибка при выполнении команды: " + e.getMessage());
        }
    }
}
