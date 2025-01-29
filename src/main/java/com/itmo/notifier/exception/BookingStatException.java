package com.itmo.notifier.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BookingStatException extends RuntimeException {
    private HttpStatus httpStatus;
    public BookingStatException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }
}
