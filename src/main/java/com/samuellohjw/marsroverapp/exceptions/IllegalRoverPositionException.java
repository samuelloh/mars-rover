package com.samuellohjw.marsroverapp.exceptions;

public class IllegalRoverPositionException extends Exception {
    public IllegalRoverPositionException() {
        super("Illegal Rover position as it is already occupied by another Rover");
    }

    public IllegalRoverPositionException(String message) {
        super(message);
    }
}
