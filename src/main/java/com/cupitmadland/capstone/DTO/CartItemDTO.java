package com.cupitmadland.capstone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) class representing Cart Item data.
 * Used to hold Cart Item data while updating the cart, processing checkout and displaying order details.
 */
@Data
@NoArgsConstructor
public class CartItemDTO {

    /**
     * The ID of the product associated with the Cart Item.
     */
    public Long productId;

    /**
     * The quantity of the product in the Cart Item.
     */
    public Integer productQuantity;

}
