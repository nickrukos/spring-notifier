package com.itmo.notifier.client;

import feign.RetryableException;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class DwhRetryer implements Retryer {
    private final int maxAttempts;
    private final long backoff;
    private int attempt = 1;

    @Override
    public void continueOrPropagate(RetryableException ex){
        System.out.println("попытка " + attempt);
        if(attempt++ >= maxAttempts){
            throw ex;
        }
        try{
            TimeUnit.SECONDS.sleep(backoff);
        }catch(InterruptedException e){

        }
    }

    @Override
    public Retryer clone() {
        return new DwhRetryer(maxAttempts,backoff);
    }
}
