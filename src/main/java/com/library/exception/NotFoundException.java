package com.library.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private String message;
    public NotFoundException(String message) {
        super(message);
    }


}
