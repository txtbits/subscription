package com.subscription.mailservice.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


public class RetrySendMail {

    @Getter
    @Setter
    private boolean isDone = false;

    @Value("${mail.attemps}")
    private int configuredAttemps;

    private int attemps = 0;

    public void addNewAttempt(){
        attemps++;
    }

    public boolean hasToRetry(){
        return !isDone && configuredAttemps >= attemps;
    }
}
