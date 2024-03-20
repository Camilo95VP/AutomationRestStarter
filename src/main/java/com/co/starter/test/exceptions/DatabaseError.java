package com.co.starter.test.exceptions;

public class DatabaseError extends AssertionError{
    public DatabaseError(String message) {
        super(message);
    }
}
