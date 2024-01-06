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

@Controller
public class AuthoController {

    private final CustomerService customerService;
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



    //@GetMapping("/order_details")
    //public String orderDetails() { return "order_details"; }

    @GetMapping("/our_story")
    public String ourStory() { return "our_story"; }

   // @GetMapping("/contact_us")
   // public String contactUs() { return "contact_us"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("CandleCustomer", customer);
        return "register";
    }

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

    @GetMapping("/newsletter")
    public String customer(Model model){
        List<Customer> customerList = customerService.findAllCustomer();

        model.addAttribute("customer", customerList);

        return "newsletter";
    }

}
