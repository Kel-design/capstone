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

/**
 * Controller class for managing endpoints related to the Contact Us page.
 * Handles interactions related to displaying the contact form, saving contact information,
 * and displaying a thank-you page.
 */
@Controller
public class ContactController {

    // Autowired dependency for the controller
    private final CustomerService customerService;

    /**
     * Constructor for ContactController
     * @param customerService Service for customer-related operations.
     */
    @Autowired
    public ContactController(CustomerService customerService) {this.customerService = customerService;}

    /**
     * Display the contact form on the Contact Us page.
     *
     * @param model Model object for adding attributes.
     * @return String representing the view name for the contact form.
     */
    @GetMapping("/contact_us")
    public String showContactForm(Model model) {
        model.addAttribute("contactInfo", new Customer());
        return "contact_us";
    }

    /**
     * Save the provided contact information to the database.
     *
     * @param contactInfo   Customer entity representing the contact information.
     * @param result        BindingResult for validating form input.
     * @param model         Model object for adding attributes.
     * @return String representing the redirection to the thank-you page or the contact form if validation fails.
     */
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

    /**
     * Display the thank-you page after successfully submitting the contact form.
     *
     * @return String representing the view name for the thank-you page.
     */
    @RequestMapping("/thank_you")
    public String showThankYouPage(){
        return "thank_you";
    }
}
