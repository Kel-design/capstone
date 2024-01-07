package com.cupitmadland.capstone.controller;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Used for endpoints related to the Contact Us page
@Controller
public class ContactController {


    private final CustomerService customerService;
    @Autowired
    public ContactController(CustomerService customerService) {this.customerService = customerService;}

    @GetMapping("/contact_us")
    public String showContactForm(Model model) {
        model.addAttribute("contactInfo", new Customer());
        return "contact_us";
    }

    @PostMapping("/contact_us/save")
    public String saveContactInfo(@Valid @ModelAttribute("contactInfo") Customer contactInfo, BindingResult result, Model model){
        // Validate form input
        if (result.hasErrors()){
            model.addAttribute("contactInfo", contactInfo);
            return "contact_us";
        }

        // Save the contact information to the database
        customerService.saveCustomer(contactInfo);
        return "redirect:/thank_you";
    }
@RequestMapping("/thank_you")
    public String showThankYouPage(){
        return "thank_you";
    }
}
