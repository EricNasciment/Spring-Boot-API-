package com.spring.api.simpleproject.Exceptions;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Global_Exception_Handler")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{


    @Value("${server.error.include-stacktrace}")
    private String printStacktrace;

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException methodArgumentNotValidException,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request){
            
         ErrorResponse errorResponse = new ErrorResponse(
           HttpStatus.UNPROCESSABLE_ENTITY.value(),
          "Validation error. Check 'errors' field for details.");  

           for(FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()){ 
               errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
           }
           return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }  

}
