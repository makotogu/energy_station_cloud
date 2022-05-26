package com.gujiacheng.commandservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    @PutMapping("/")
    public String index() {
        return "Hello World!";
    }
}