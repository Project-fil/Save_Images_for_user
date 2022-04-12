package com.example.save_images_for_user.exception;

public class EntityExistException extends RuntimeException {

    public EntityExistException(String message) {
        super(message);
    }

    public EntityExistException() {}

}
