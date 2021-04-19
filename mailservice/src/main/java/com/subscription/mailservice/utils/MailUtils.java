package com.subscription.mailservice.utils;

import com.subscription.mailservice.model.Subscription;
import org.springframework.mail.SimpleMailMessage;

public class MailUtils {

    public final static String DEFAULT_EMAIL_FROM = "noreply@subscription.com";

    private final static String SUBJECT = "Welcome to Adidas Subscription ";
    private final static String BODY_HEADER = "Congratulations";
    private final static String BODY_TEXT = "You have been subscribed successfully";
    private final static String BODY_FOOTER = "Thank you!";
    private final static String BLANK = " ";

    public static SimpleMailMessage buildSimpleMailMessage(Subscription s) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(DEFAULT_EMAIL_FROM);
        message.setTo(s.getEmail());
        message.setSubject(buildSubject(s));
        message.setText(buildBody(s));
        return message;
    }

    private static String buildSubject(Subscription s){
        return SUBJECT + s.getFirstName();
    }

    private static String buildBody(Subscription s){
        StringBuilder sb = new StringBuilder();
        return sb.append(BODY_HEADER).append(BLANK).append(s.getFirstName())
                .append(System.lineSeparator()).append(BODY_TEXT).append(BLANK)
                .append(s.getNewsLetterId()).append(System.lineSeparator()).append(BODY_FOOTER)
                .append(System.lineSeparator()).toString();
    }
}
