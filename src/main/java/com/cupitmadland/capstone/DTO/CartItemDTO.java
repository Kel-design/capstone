package com.cupitmadland.capstone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

// Used to help hold Cart Item data while using it to update cart, checkout and show order details.
@Data
@NoArgsConstructor
public class CartItemDTO {

    public Long productId;

    public Integer productQuantity;

}
