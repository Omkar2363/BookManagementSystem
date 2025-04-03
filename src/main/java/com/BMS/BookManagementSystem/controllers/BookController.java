package com.BMS.BookManagementSystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/testDeploy")
    public String testController(){
        return "Book Management System Deployed Successfully";
    }
}
