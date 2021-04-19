package com.subscription.publicservice.controller;

import com.subscription.publicservice.model.Subscription;
import com.subscription.publicservice.service.PublicService;
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
import java.util.List;
import java.util.Map;

@RestController
@Api(value="Public Subscription Controller", description="Operations which can be to manage Subscriptions")
public class PublicController {

    @Autowired
    PublicService publicService;

    private final Logger log = LoggerFactory.getLogger(PublicController.class);

    @ApiOperation(value = "Get all subscriptions", notes = "This operation returns all the subscriptions")
    @RequestMapping(value = "/subscription", method = RequestMethod.GET)
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        //TODO Revisar publicService::getSubscriptions no funciona
        try {
            log.info("[PUBLIC] Getting all subscriptions");
            List<Subscription> list = publicService.getSubscriptions();
            if (!list.isEmpty()) {
                return ResponseEntity.ok(list);
            } else {
                return (ResponseEntity<List<Subscription>>) ResponseEntity.badRequest();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value="Create a new subscription", response = Subscription.class)
    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public ResponseEntity<Object> createSubscription(@Valid @RequestBody Subscription subscription) {
        log.info("[PUBLIC] Creating new subscription");
        Subscription response = publicService.createSubscription(subscription);
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
