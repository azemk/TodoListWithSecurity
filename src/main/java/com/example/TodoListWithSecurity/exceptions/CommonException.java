package com.example.TodoListWithSecurity.exceptions;

public class CommonException extends RuntimeException{
    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }
}
