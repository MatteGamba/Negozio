package com.gamba.negozio.exception;

public class MangaNotFoundException extends RuntimeException{
    public MangaNotFoundException(String message){
        super("Manga by id " + message + " was not found!");
    }
}
