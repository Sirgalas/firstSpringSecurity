package ru.sergalas.FirstSecurity.entities.users.exeption;

public class UserValidAuthException extends RuntimeException {
    public UserValidAuthException(String message) {
        super(message);
    }
}
