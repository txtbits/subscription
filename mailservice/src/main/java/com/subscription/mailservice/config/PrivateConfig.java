package com.subscription.mailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PrivateConfig {

    @Value("${private.user:default}")
    String user;

    @Value("${private.pass:default}")
    String pass;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        System.out.println("USER:PASS privateconfig" + user+":"+pass);
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user, pass));
        return restTemplate;
    }

}
