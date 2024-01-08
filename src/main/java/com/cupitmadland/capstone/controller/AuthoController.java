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

import java.util.List;

/**
 * Controller class for managing general page endpoints and handling the Newsletter Signup/Registration.
 * Handles interactions related to home, candles, scents, single product, our story, login, registration and newsletter pages.
 */
@Controller
public class AuthoController {

    // Autowired dependency for the controller
    private final CustomerService customerService;

    /**
     * Constructor for AuthoController.
     *
     * @param customerService Service for customer-related operations.
     */
    @Autowired
    public AuthoController(CustomerService  customerService) {this.customerService = customerService;}

    /**
     * Display the home page.
     *
     * @return String representing the view name for the home page.
     */
    @GetMapping("/home")
    public String home() { return "home"; }

    /**
     * Display the candles page.
     *
     * @return String representing the view name for the candles page.
     */
    @GetMapping("/candles")
    public String candles() { return "candles"; }

    /**
     * Display the scents page.
     *
     * @return String representing the view name for the scents page.
     */
    @GetMapping("/scents")
    public String scents() { return "scents"; }

    /**
     * Display the single product page.
     *
     * @return String representing the view name for the single product page.
     */
    @GetMapping("/single_product")
    public String singleProduct() { return "single_product"; }

    /**
     * Display our story page.
     *
     * @return String representing the view name for our story page.
     */
    @GetMapping("/our_story")
    public String ourStory() { return "our_story"; }

    /**
     * Display the login page.
     *
     * @return String representing the view name for the login page.
     */
    @GetMapping("/login")
    public String login() { return "login"; }

    /**
     * Display the registration form for new customers.
     *
     * @param model Model object for adding attributes.
     * @return String representing the view name for the registration form.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("CandleCustomer", customer);
        return "register";
    }

    /**
     * Process the registration of a new customer.
     *
     * @param customer  Customer entity representing the customer information.
     * @param result    BindingResult for validating form input.
     * @param model     Model object for adding attributes.
     * @return String representing the redirection to the registration form with success message or error messages.
     */
    @PostMapping("register/save")
    public String registration(@Valid @ModelAttribute("CandleCustomer") Customer customer, BindingResult result, Model model){
        Customer existingCandleCustomer = customerService.findCustomerByEmail(customer.getEmail());

        if (existingCandleCustomer != null && existingCandleCustomer.getEmail() != null && !existingCandleCustomer.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()){
            model.addAttribute("CandleCustomer", customer);
            return "/register";
        }
        customerService.saveCustomer(customer);
        return "redirect:/register?success";
    }

    /**
     * Display the newsletter page.
     *
     * @param model Model object for adding attributes.
     * @return String representing the view name for the newletter page.
     */
    @GetMapping("/newsletter")
    public String customer(Model model){
        List<Customer> customerList = customerService.findAllCustomer();

        model.addAttribute("customer", customerList);

        return "newsletter";
    }

}
