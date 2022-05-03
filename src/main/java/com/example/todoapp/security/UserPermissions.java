package com.example.todoapp.security;

public enum UserPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    TODO_READ("todo:read"),
    TODO_WRITE("todo:write");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermissions() {
        return permission;
    }
}
