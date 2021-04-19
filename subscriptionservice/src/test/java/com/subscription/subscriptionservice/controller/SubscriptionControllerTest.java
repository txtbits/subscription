package com.subscription.subscriptionservice.controller;

import com.subscription.subscriptionservice.model.Subscription;
import com.subscription.subscriptionservice.service.SubscriptionServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionControllerTest {
    /*
    protected static final String BASE_URL = "http://localhost:@/subscription/";
    protected static final int PORT = 8886;

    @MockBean
    private TestRestTemplate restTemplate;

    @MockBean
    private SubscriptionServiceImpl subscriptionServiceImpl;

    private String baseUrl;

    HttpHeaders httpHeaders = new HttpHeaders();

    Subscription subscription = new Subscription(100L,"mail@mail.com", "firstName",
            "male", new Date(), true, 1L);

    @Before
    public void setup() throws Exception {
        this.baseUrl = new URL(BASE_URL.replace("@", String.valueOf(PORT))).toString();
        initMocks(this);
    }

    @Test
    public void test_getSubscriptions() {
        when(this.subscriptionServiceImpl.getSubscriptions()).thenReturn(new ArrayList<>());
        ResponseEntity<String> response = this.restTemplate.getForEntity(this.baseUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void test_createSubscription() {
        httpHeaders.set("Content-Type", "application/json");
        when(this.subscriptionServiceImpl.getSubscriptions()).thenReturn(new ArrayList<>());
        HttpEntity<Object> request = new HttpEntity<>(subscription, httpHeaders);
        ResponseEntity<String> response = this.restTemplate.postForEntity(this.baseUrl, request, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void test_deleteSubscription() {
        //when(this.subscriptionServiceImpl.deleteSubscription(anyLong())).thenReturn(Boolean.TRUE);
        ResponseEntity<Subscription> response = this.restTemplate.exchange(this.baseUrl + subscription.getId(), HttpMethod.DELETE, null, Subscription.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    */
}
