package com.example.server.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "error-page/error";
    }
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
