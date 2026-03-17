package com.spring.api.simpleproject.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomObjectNotFoundException extends EntityNotFoundException {

    public CustomObjectNotFoundException(String message) {
        super(message);
    }

}