package com.gamba.negozio.exception;

public class SerieNotFoundException extends RuntimeException{
    public SerieNotFoundException(String message){
        super("Serie by id " + message + " was not found!");
    }
}
