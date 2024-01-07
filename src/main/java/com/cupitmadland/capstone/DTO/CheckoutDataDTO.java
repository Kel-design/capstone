package com.cupitmadland.capstone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;


// Used to hold data obtained during the checkout in order to create a customer and show order details.
@Data
@NoArgsConstructor
public class CheckoutDataDTO {

    public String nameOnCard;

    public String expDate;

    public String creditCardNum;

    public String securityCode;

    public String address;

    public String city;

    public String state;

    public String zipCode;

    public String email;


}
