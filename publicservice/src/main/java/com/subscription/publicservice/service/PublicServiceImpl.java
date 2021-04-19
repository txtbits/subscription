package com.subscription.publicservice.service;

import com.subscription.publicservice.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PublicServiceImpl implements PublicService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${private.url}")
    String url;

    private final Logger log = LoggerFactory.getLogger(PublicServiceImpl.class);

    @Override
    public List<Subscription> getSubscriptions() {
        log.info("Getting all subscriptions from PublicService through SubscriptionService");
        ResponseEntity<Subscription[]> subscriptionsArrayRE = restTemplate.exchange(url + "/subscription", HttpMethod.GET, null, Subscription[].class);
        return Arrays.asList(subscriptionsArrayRE.getBody());
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        log.info("Creating subscription from PublicService through SubscriptionService");
        return restTemplate.postForObject(url + "/subscription", subscription, Subscription.class);
    }
}
