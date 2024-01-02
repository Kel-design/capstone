package com.cupitmadland.capstone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

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
