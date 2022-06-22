package com.gamba.negozio.exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String message){
        super("Game by id " + message + " was not found!");
    }
}
