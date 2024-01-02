package com.cupitmadland.capstone.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO {

    public Long productId;

    public Integer productQuantity;

}
