package com.bankbroker.loanapp.exception;


public class UserAlreadyLoggedInException extends RuntimeException {

    public UserAlreadyLoggedInException() {
        super("User already logged in on another device");
    }
}