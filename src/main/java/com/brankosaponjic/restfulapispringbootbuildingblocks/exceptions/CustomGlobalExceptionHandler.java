package com.brankosaponjic.restfulapispringbootbuildingblocks.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // MethodArgumentNotValidException

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
                MethodArgumentNotValidException ex,
                HttpHeaders headers,
                HttpStatus status,
                WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                "From MethodArgumentNotValidException in CustomGlobalExceptionHandler",
                ex.getMessage()
        );

        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }

    // HttpRequestMethodNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                "Method Not Allowed. From MethodArgumentNotValidException in CustomGlobalExceptionHandler",
                ex.getMessage()
        );

        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
