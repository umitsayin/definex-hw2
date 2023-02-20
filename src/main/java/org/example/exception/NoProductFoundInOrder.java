package org.example.exception;

public class NoProductFoundInOrder extends RuntimeException {
    public NoProductFoundInOrder(String message) {
        super(message);
    }
}
