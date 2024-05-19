package com.library.exception;

import lombok.Getter;

@Getter
public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }

    private String message;
}
