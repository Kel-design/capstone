package com.cupitmadland.capstone.controller;

import com.cupitmadland.capstone.entity.CustOrder;
import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.service.CustOrderService;
import com.cupitmadland.capstone.service.CustomerService;
import com.cupitmadland.capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CustOrderController {


    private final CustOrderService custOrderService;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public CustOrderController(CustOrderService custOrderService, CustomerService customerService, ProductService productService){
        this.custOrderService = custOrderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/shopping_cart")
    public String showShoppingCart(){

        // Shows the shopping cart page
        return "shopping_cart";
    }

public String processCheckout(@ModelAttribute("custOrder")CustOrder custOrder,
                              @ModelAttribute("typeName") String nameOnCard,
                              @ModelAttribute("typeExpDate") String expDate,
                              @ModelAttribute("typeCardNum") String creditCardNum,
                              @ModelAttribute("typeCvv") String securityCode,
                              @ModelAttribute("address") String address,
                              @ModelAttribute("city") String city,
                              @ModelAttribute("state") String state,
                              @ModelAttribute("zipCode") String zipCode,
                              @ModelAttribute("email") String email){


        // Extract firstName, middleName and lastName from nameOnCard
    String[] nameParts = nameOnCard.split("\\s+", 3);

    // Initialize variable for firstName, middleName and lastName
    String firstName = "";
    String middleName = "";
    String lastName = "";

    // Assign values based on the number of parts
    if (nameParts.length > 0){
        firstName = nameParts[0];
    }
    if (nameParts.length > 1){
        middleName = nameParts[1];
    }
    if (nameParts.length > 2){
        lastName = nameParts[2];
    }

        // Create a new Customer instance
    Customer customer = new Customer();
    customer.setFirstName(firstName);
    customer.setMiddleName(middleName);
    customer.setLastName(lastName);

    // Set other Customer details
    customer.setEmail(email);
    customer.setAddress(address);
    customer.setCity(city);
    customer.setState(state);
    customer.setZipCode(zipCode);

    // Save the customer
    customerService.saveCustomer(customer);

    // TODO TODO TODO
    // Now have customer object needed for CustOrder processing
    // Need to include list of products (including size) in shopping cart, quantity of products, and Total cost.

    // Save the CustOrder
    custOrderService.saveCustOrder(custOrder);

    return "order_details";

}
}
