package com.subscription.subscriptionservice.service;

import com.subscription.subscriptionservice.model.Subscription;
import com.subscription.subscriptionservice.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Override
    public List<Subscription> getSubscriptions() {
        log.info("Getting all subscriptions from SubscriptionService");
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        log.info("Creating subscription from SubscriptionService");
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(long id) {
        log.info("Deleting subscription from SubscriptionService");
        subscriptionRepository.deleteById(id);
    }


}
