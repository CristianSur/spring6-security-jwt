package com.example.sample.exception.authentication;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionResponseModel {

    Integer status;
    String error;
    String cause;
    String message;

    @Builder.Default
    String timestamp = Instant.now().toString();
}
