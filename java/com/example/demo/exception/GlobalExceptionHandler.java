package com.example.demo.exception;

import com.example.demo.util.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /* Handle resource not found exception, returns object of error info along with NOT FOUND */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleResourceNotFoundException (
            ResourceNotFoundException resourceNotFoundException,
            WebRequest webRequest) {

        ErrorInfo errorInfo = new ErrorInfo(
                new Date(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

}
