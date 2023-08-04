package com.example.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public String greet() {
        return "Hello, welcome to the secured endpoint";
    }
}
