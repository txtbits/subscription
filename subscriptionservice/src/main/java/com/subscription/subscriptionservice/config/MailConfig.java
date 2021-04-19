package com.subscription.subscriptionservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MailConfig {

    @Value("${mail.user:default}")
    String user;

    @Value("${mail.pass:default}")
    String pass;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user, pass));
        return restTemplate;
    }

}
