package com.example.TodoListWithSecurity.model.enums;

public enum UserAuthorities {
    READ("read"),
    WRITE("write");

    private final String permission;

    UserAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
