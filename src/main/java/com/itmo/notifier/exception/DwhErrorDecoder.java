package com.itmo.notifier.exception;

import com.itmo.notifier.configuration.NotifierConfiguration;
import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
public class DwhErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    private List<Integer> retryableStatuses() {
        return Arrays.asList(
                HttpStatus.BAD_GATEWAY.value(),
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                HttpStatus.GATEWAY_TIMEOUT.value(),
                HttpStatus.INSUFFICIENT_STORAGE.value(),
                HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NO_CONTENT.value(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.NOT_EXTENDED.value());
    }
    @Override
    public Exception decode(String methodKey, Response response){
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        if(retryableStatuses().contains(response.status())){
            log.info("Ошибка при выполнении " +methodKey + " Код ошибки " + response.status());
            return new RetryableException(response.status(),
                                          exception.getMessage(),
                                          response.request().httpMethod(),
                                          exception,
                                          50L,
                                          response.request());
        }
        return defaultErrorDecoder.decode(methodKey,response);
    }
}
