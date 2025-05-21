package com.rizoma.rizoma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateDataExcepction extends RuntimeException {
    
    public DuplicateDataExcepction(String message) {
        super(message);
    }
}
