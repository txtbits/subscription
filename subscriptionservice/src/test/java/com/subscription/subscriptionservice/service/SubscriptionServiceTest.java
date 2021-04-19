package com.subscription.subscriptionservice.service;

import com.subscription.subscriptionservice.model.Subscription;
import com.subscription.subscriptionservice.repository.SubscriptionRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
class SubscriptionServiceTest {
    /*
    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private SubscriptionServiceImpl subscriptionServiceImpl;

    Subscription subscription = new Subscription(100L,"mail@mail.com", "firstName",
            "male", new Date(), true, 1L);

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void test_getSubscriptions_With_Results() throws Exception {
        List<Subscription> subscriptionsList = new ArrayList<>();
        subscriptionsList.add(subscription);
        when(subscriptionRepository.findAll()).thenReturn(subscriptionsList);
        assertEquals(1, this.subscriptionServiceImpl.getSubscriptions().size());
    }

    @Test
    public void test_getSubscriptions_No_Results() throws Exception {
        when(this.subscriptionRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(0, this.subscriptionServiceImpl.getSubscriptions().size());
    }

    @Test
    void test_getSubscriptions_AssertThrowsException() throws Exception {
        when(this.subscriptionRepository.findAll()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            this.subscriptionServiceImpl.getSubscriptions();
        });
    }

    @Test
    public void test_createSubscription() throws Exception {
        when(this.subscriptionServiceImpl.createSubscription(Mockito.any())).thenReturn(subscription);
    }

    @Test
    public void test_createSubscription_AssertThrowsException() throws Exception {
        assertThrows(NullPointerException.class, () -> {
            this.subscriptionServiceImpl.createSubscription(null);
        });
    }

    @Test
    public void test_deleteSubscription_Id() throws Exception {

        when(this.subscriptionRepository.findById(Mockito.anyString())).thenReturn(Optional.of(subscription));
        doNothing().when(this.subscriptionRepository).deleteById(anyLong());
        this.subscriptionServiceImpl.deleteSubscription(anyLong());
    }

    @Test
    public void test_deleteSubscription_No_Id() throws Exception {
        given(this.subscriptionRepository.findById(Mockito.anyString()))
        .willAnswer(invocation -> {throw new NotFoundException("Not found exception");});
        assertThrows(NotFoundException.class, () -> {
            this.subscriptionServiceImpl.deleteSubscription(anyLong());
        });
    }

    @Test
    public void test_deleteSubscription_AssertThrowsException() throws Exception {
        assertThrows(NotFoundException.class, () -> {
            this.subscriptionServiceImpl.deleteSubscription(new Long(null));
        });
    }
    */
}