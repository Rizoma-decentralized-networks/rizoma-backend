package com.rizoma.rizoma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateMarkException extends RuntimeException {
    
    public DuplicateMarkException(String message) {
        super(message);
    }
}
