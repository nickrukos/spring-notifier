package com.itmo.notifier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InterruptedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InterruptedException.class})
    protected ResponseEntity<Object> InterruptedHandle(InterruptedException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
