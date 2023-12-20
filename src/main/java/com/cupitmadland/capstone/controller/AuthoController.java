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
    public String home() { return "home"; }

    @GetMapping("/candles")
    public String candles() { return "candles"; }

    @GetMapping("/scents")
    public String scents() { return "scents"; }

    @GetMapping("/single_product")
    public String singleProduct() { return "single_product"; }

    @GetMapping("/shopping_cart")
    public String shoppingCart() { return "shopping_cart"; }

    @GetMapping("/our_story")
    public String ourStory() { return "our_story"; }

    @GetMapping("/contact_us")
    public String contactUs() { return "contact_us"; }




}
