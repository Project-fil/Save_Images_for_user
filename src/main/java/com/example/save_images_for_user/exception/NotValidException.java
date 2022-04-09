package com.example.save_images_for_user.exception;

public class NotValidException extends RuntimeException{

    public NotValidException(String message) {
        super(message);
    }

    public NotValidException() {}
}
