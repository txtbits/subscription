package com.subscription.subscriptionservice.controller;

import com.subscription.subscriptionservice.model.Subscription;
import com.subscription.subscriptionservice.repository.SubscriptionRepository;
import com.subscription.subscriptionservice.service.MailService;
import com.subscription.subscriptionservice.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@Api(value="Private Subscription Controller", description="Operations which can be to manage Subscriptions")
public class SubscriptionController {

    private final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private MailService mailService;
    
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @ApiOperation(value = "Get all subscriptions", notes = "This operation returns all the subscriptions")
    @RequestMapping(value = "/subscription", method = RequestMethod.GET)
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        try {
            log.info("[PRIVATE] Getting all subscriptions");
            return ResponseEntity.status(HttpStatus.OK).body(subscriptionService.getSubscriptions());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value="Create a new subscription", response = Subscription.class)
    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public ResponseEntity<Object> createSubscription(@Valid @RequestBody Subscription subscription) {
        try {
            Subscription s = subscriptionRepository.findOneByEmail(subscription.getEmail());
            if (s != null) {
                log.error("The email account is already subscribed");
                return new ResponseEntity<>("The email account is already subscribed", HttpStatus.CONFLICT);
            }
            log.info("[PRIVATE] Creating new subscription");
            Subscription subscriptionDB = subscriptionService.createSubscription(subscription);
            log.info("[PRIVATE] Subscription persisted");

            CompletableFuture.runAsync(() -> {
                mailService.sendMail(subscriptionDB);
            });
            return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionDB);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "Delete a subscription", notes = "Delete subscription by identifier")
    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteSubscription(
            @ApiParam(name = "id", value = "Identifier of a subscription", required = true)
            @PathVariable("id") Long id) {
        try {
            Optional<Subscription> s = subscriptionRepository.findById(id);
            if (s.isPresent()) {
                subscriptionService.deleteSubscription(id);
                return ResponseEntity.ok(true);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
