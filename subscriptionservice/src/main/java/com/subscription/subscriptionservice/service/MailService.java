package com.subscription.subscriptionservice.service;

import com.subscription.subscriptionservice.model.Subscription;

public interface MailService {

	public String sendMail(Subscription subscription);

}
