package com.subscription.mailservice.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Subscription implements Serializable {

    private Long id;
    private String email;
    private String firstName;
    private String gender;
    private Date dateOfBirth;
    private boolean consentFlag;
    private Long newsLetterId;

    public Subscription() { }

}