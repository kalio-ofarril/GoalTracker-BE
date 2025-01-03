package com.goaltracker.GoalTracker.Utils.Exceptions;

public class UserException extends Exception {

    public UserException() {
        super("User Exception.");
    }

    public UserException(String messaage) {
        super(messaage);
    }

}
