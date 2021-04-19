package com.subscription.mailservice.service;

import com.subscription.mailservice.component.BasicMailSenderComponent;
import com.subscription.mailservice.model.Subscription;
import com.subscription.mailservice.utils.RetrySendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    BasicMailSenderComponent basicMailSenderComponent;

    private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

//    @Autowired
//    private RestTemplate restTemplate;

    @Value("${private.url}")
    String url;

    @Override
    public void sendMail(Subscription subscription) {
        if(sendEmailWithAttemps(subscription)){
            //putSubscriptionNotified(subscription);
            log.warn("Trying a new email sending attempt");
        }
    }

    private boolean sendEmailWithAttemps(Subscription subscription) {
        RetrySendMail retry = new RetrySendMail();
        while (retry.hasToRetry()) {
            try {
                basicMailSenderComponent.sendSimpleMessage(subscription);
                retry.setDone(true);
            } catch (MailException ex) {
                retry.addNewAttempt();
            }
        }
        return retry.isDone();
    }

//    private void putSubscriptionNotified(Subscription subscription) {
//        restTemplate.put(url + Constants.UPDATE_NOTIFIED_PATH, subscription);
//    }
}
