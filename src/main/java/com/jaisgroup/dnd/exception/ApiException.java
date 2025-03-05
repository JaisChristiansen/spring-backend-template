package com.jaisgroup.dnd.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    @Getter
    private HttpStatus status;
    @Getter
    private String message;

    public ApiException() {}

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
