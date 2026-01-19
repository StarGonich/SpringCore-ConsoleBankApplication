package org.example;

import org.example.service.OperationsConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        var consoleListener = context.getBean("operationsConsoleListener", OperationsConsoleListener.class);
        consoleListener.openConsole();
        context.close();
    }
}
