package com.mediatype.examplework.handler;

import com.mediatype.examplework.exception.FileExistsException;
import com.mediatype.examplework.exception.NotFoundException;
import com.mediatype.examplework.exception.SaveFileException;
import com.mediatype.examplework.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> notFoundException(NotFoundException exc){
        return new ResponseEntity<>(new Response(exc.getMessage()
                , exc.getStatus(), exc.getCode()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaveFileException.class)
    public ResponseEntity<Response> saveFileException(SaveFileException exc){
        return new ResponseEntity<>(new Response(exc.getMessage(), exc.getStatus()
                , exc.getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileExistsException.class)
    public ResponseEntity<Response> fileAlreadyExists(FileExistsException exc){
        return new ResponseEntity<>(new Response(exc.getMessage(), exc.getStatus(), exc.getCode()), HttpStatus.FOUND);
    }
}
