package com.cupitmadland.capstone;

import com.cupitmadland.capstone.entity.CartItem;
import com.cupitmadland.capstone.entity.Product;
import com.cupitmadland.capstone.repository.CartItemRepository;
import com.cupitmadland.capstone.repository.ProductRepository;
import com.cupitmadland.capstone.repository.ShoppingCartRepository;
import com.cupitmadland.capstone.service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddToCartTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test
    public void testAddToCart() {

        // Given
        Product product = createSampleProduct();
        productRepository.save(product);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);
        cartItem.setProduct(product);

        // When
        shoppingCartService.addToCart(cartItem);

        // Then
        assertEquals(1, cartItemRepository.count());
        CartItem savedCartItem = cartItemRepository.findAll().get(0);
        assertEquals(2, savedCartItem.getQuantity());
        assertEquals(product.getId(), savedCartItem.getProduct().getId());
    }

    private Product createSampleProduct() {
        Product product = new Product();
        product.setName("Rose - Large");
        product.setDescription("Rose emanates a soothing and romantic floral fragrance, encapsulating the essence of blooming roses.");
        product.setScent("Rose");
        product.setSize("Large");
        product.setPrice(new BigDecimal("30.00"));
        product.setStockQuantity(20);
        return product;
    }
}
