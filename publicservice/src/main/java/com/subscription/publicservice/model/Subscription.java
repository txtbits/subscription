package com.subscription.publicservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subscription implements Serializable {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ApiModelProperty(notes = "User email", required = true)
    @NotNull
    private String email;

    @ApiModelProperty(notes = "First name")
    private String firstName;

    @ApiModelProperty(notes = "Gender")
    private String gender;

    @ApiModelProperty(notes = "Date of Birth", required = true)
    @NotNull
    private Date dateOfBirth;

    @ApiModelProperty(notes= "Check used for accept the terms of use", required = true)
    @NotNull
    private boolean consentFlag;
    
    @ApiModelProperty(notes = "Id of the newsLetter to subscribe the user", required = true)
    @NotNull
    private Long newsLetterId;

    public Subscription() { }
}
