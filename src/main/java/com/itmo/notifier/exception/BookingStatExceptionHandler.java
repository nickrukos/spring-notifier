package com.itmo.notifier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookingStatExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {BookingStatException.class})
    protected ResponseEntity<?> handleException(BookingStatException ex,
                                                     WebRequest request){
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ex.getMessage());
    }
}
