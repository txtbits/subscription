package com.subscription.mailservice.service;

import com.subscription.mailservice.model.Subscription;

public interface MailService {

    public void sendMail(Subscription subscription);

}
