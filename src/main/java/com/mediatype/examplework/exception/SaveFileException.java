package com.mediatype.examplework.exception;

import org.springframework.http.HttpStatus;

public class SaveFileException extends GlobalException {
    public SaveFileException(String message, HttpStatus status, String code) {
        super(message, status, code);
    }
}
