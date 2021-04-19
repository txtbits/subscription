package com.subscription.mailservice.controller;

import com.subscription.mailservice.model.Subscription;
import com.subscription.mailservice.service.MailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @ApiOperation(value = "Send email", notes = "Send an email to subscription email")
    @RequestMapping(method = RequestMethod.POST, value = "/sendMail")
    public void sendMail(@ApiParam(name = "subscriptionData", value = "Subscription data in JSON", required = true)
                         @RequestBody Subscription subscription) {
        mailService.sendMail(subscription);
    }
}