package com.subscription.email.controller;

import com.subscription.email.model.Subscription;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @RequestMapping(method = RequestMethod.POST, value = "/sendMail")
    @ResponseBody
    public String sendMail(@RequestBody Subscription subscription) {
        return "sent";
    }
}