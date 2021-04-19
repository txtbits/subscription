package com.subscription.mailservice.component;

import com.subscription.mailservice.model.Subscription;
import com.subscription.mailservice.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class BasicMailSenderComponent {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(Subscription s) throws MailException {
        mailSender.send(MailUtils.buildSimpleMailMessage(s));
    }
}
