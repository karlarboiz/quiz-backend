package com.example.demo.exception;

public class FolderNotFoundException extends RuntimeException{

    public FolderNotFoundException(String message){
        super(message);
    }
}
