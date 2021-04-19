package com.subscription.subscriptionservice.service;

import com.subscription.subscriptionservice.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    public List<Subscription> getSubscriptions();

    public Subscription createSubscription(Subscription subscription);

    public void deleteSubscription(long id);


}
