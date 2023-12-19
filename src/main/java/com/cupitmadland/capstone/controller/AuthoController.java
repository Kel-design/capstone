package com.cupitmadland.capstone.controller;

import com.cupitmadland.capstone.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthoController {

    private CustomerService customerService;
    @Autowired
    public AuthoController(CustomerService  customerService) {this.customerService = customerService;}

    @GetMapping("/home")
    public String home(){ return "home"; }
}
