package com.mediatype.examplework.exception;

import org.springframework.http.HttpStatus;

public class FileExistsException extends GlobalException {
    public FileExistsException(String message, HttpStatus status, String code) {
        super(message, status, code);
    }
}
