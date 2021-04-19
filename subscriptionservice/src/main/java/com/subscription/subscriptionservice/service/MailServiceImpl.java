package com.subscription.subscriptionservice.service;

import com.subscription.subscriptionservice.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mail.url}")
    String mailServiceUrl;

    private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public String sendMail(Subscription subscription) {
        log.info("Sending subscription to mail service");
        ResponseEntity<?> response = restTemplate.postForEntity(mailServiceUrl + "/sendMail", subscription, String.class);
        return Objects.requireNonNull(response.getBody()).toString();
    }
}
