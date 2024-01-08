package com.cupitmadland.capstone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data Transfer Object (DTO) class representing data obtained during the checkout process.
 * Used to hold checkout data for creating a customer and displaying order details.
 */
@Data
@NoArgsConstructor
public class CheckoutDataDTO {

    /**
     * The name on the credit card used during checkout.
     */
    public String nameOnCard;

    /**
     * The expiration date of the credit card used during checkout.
     */
    public String expDate;

    /**
     * The credit card number used during checkout.
     */
    public String creditCardNum;

    /**
     * The security code of the credit card used during checkout.
     */
    public String securityCode;

    /**
     * The address associated with the checkout process.
     */
    public String address;

    /**
     * The city associated with the checkout process.
     */
    public String city;

    /**
     * The state associated with the checkout process.
     */
    public String state;

    /**
     * The Zip code associated with the checkout process.
     */
    public String zipCode;

    /**
     * The email address associated with the checkout process.
     */

    public String email;


}
