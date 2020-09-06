package com.subscription.subscriptionservice.service;

import com.subscription.subscriptionservice.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ClientMailService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	protected String mailServiceUrl;
	
	public ClientMailService(String mailServiceUrl) {
		this.mailServiceUrl = mailServiceUrl;
	}
	
	public String sendMail(Subscription subscription) {
		ResponseEntity<?> response = restTemplate.postForEntity(mailServiceUrl + "/sendMail", subscription, String.class);
		return Objects.requireNonNull(response.getBody()).toString();
	}
}
