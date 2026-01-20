package org.example.operations;

public interface Operation {
    void execute(String[] args);
    OperationType getType();
}
