package com.subscription.subscriptionservice;

import com.subscription.subscriptionservice.controller.SubscriptionController;
import com.subscription.subscriptionservice.service.ClientMailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {ClientMailService.class}))
public class SubscriptionserviceApplication {

	public static final String EMAIL_URL = "http://EMAIL";

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionserviceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ClientMailService mailService() {
		return new ClientMailService(EMAIL_URL);
	}

	@Bean
	public SubscriptionController subscriptionController() {
		return new SubscriptionController(mailService());
	}

	@Bean
	public Docket subscriptionApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.subscription.subscriptionservice.controller"))
				.build();
	}

}
