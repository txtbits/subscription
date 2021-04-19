package com.subscription.publicservice.service;

import com.subscription.publicservice.model.Subscription;

import java.util.List;

public interface PublicService {

    public List<Subscription> getSubscriptions();

    public Subscription createSubscription(Subscription subscription);

}
