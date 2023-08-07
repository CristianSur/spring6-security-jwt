package com.example.sample.exception.authentication;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({AlreadyRegisteredException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponseModel handleException(Exception exception) {
        log.error(exception.getMessage(), exception);

        return ExceptionResponseModel.builder()
                .status(HttpStatus.CONFLICT.value())
                .cause(exception.getCause() == null ? "manual thrown" : exception.getCause().toString())
                .error(exception.getClass().getName())
                .message(exception.getMessage())
                .build();
    }

}
