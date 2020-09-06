package com.subscription.subscriptionservice.controller;

import com.subscription.subscriptionservice.model.Subscription;
import com.subscription.subscriptionservice.repository.SubscriptionRepository;
import com.subscription.subscriptionservice.service.ClientMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value="Subscription Controller", description="Operations which can be to manage Subscriptions")
public class SubscriptionController {

    private final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private ClientMailService clientMailService;
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionController(ClientMailService clientMailService) {
        this.clientMailService = clientMailService;
    }

    @ApiOperation(value="Create a new subscription", response = Subscription.class)
    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public ResponseEntity<Object> createSubscription(@Valid @RequestBody Subscription subscription) {
        Subscription s = subscriptionRepository.findOneByEmail(subscription.getEmail());
        
        if (s != null) {
            log.error("The email account is already subscribed");
            return new ResponseEntity<>("The email account is already subscribed", HttpStatus.CONFLICT);
        }
        Subscription response = subscriptionRepository.save(subscription);
        log.info("Subscription persisted");
        
        try {
            String responseMailService = clientMailService.sendMail(subscription);
            if (!responseMailService.equals("sent")) {
                log.error("Error sending email");
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
            log.info("Mail has been sended correctly");
        } catch (Exception e) {
            log.error("Error connecting with mail service", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
