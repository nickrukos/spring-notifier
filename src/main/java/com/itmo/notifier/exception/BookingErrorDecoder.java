package com.itmo.notifier.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BookingErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() < 200 || response.status() > 200) {
            throw new BookingStatException("Перечень бронирований не передался", HttpStatus.NOT_FOUND);
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
