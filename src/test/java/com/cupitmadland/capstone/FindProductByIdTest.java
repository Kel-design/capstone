package com.cupitmadland.capstone;

import com.cupitmadland.capstone.entity.Product;
import com.cupitmadland.capstone.repository.ProductRepository;
import com.cupitmadland.capstone.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class FindProductByIdTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Test
    public void testFindProductById() {
        // Given
        Product existingProduct = productRepository.save(createSampleProduct());

        // When
        Product actualProduct = productService.findProductById(existingProduct.getId());

        // Then
        assertNotNull(actualProduct);
        assertEquals(existingProduct.getName(), actualProduct.getName());
        assertEquals(existingProduct.getDescription(), actualProduct.getDescription());
        assertEquals(existingProduct.getScent(), actualProduct.getScent());
        assertEquals(existingProduct.getSize(), actualProduct.getSize());
        assertEquals(existingProduct.getPrice(), actualProduct.getPrice());
        assertEquals(existingProduct.getStockQuantity(), actualProduct.getStockQuantity());

    }

    @ParameterizedTest
    @MethodSource("provideProductIds")
    public void testFindProductByIdParameterized(Long productId){
        // Given
        Product existingProduct = createSampleProduct();
        existingProduct.setId(productId);
        productRepository.save(existingProduct);

        // When
        Product actualProduct = productService.findProductById(productId);

        // Then
        assertNotNull(actualProduct);
        assertEquals(existingProduct.getName(), actualProduct.getName());
        assertEquals(existingProduct.getDescription(), actualProduct.getDescription());
        assertEquals(existingProduct.getScent(), actualProduct.getScent());
        assertEquals(existingProduct.getSize(), actualProduct.getSize());
        assertEquals(existingProduct.getPrice(), actualProduct.getPrice());
        assertEquals(existingProduct.getStockQuantity(), actualProduct.getStockQuantity());

    }

    private static Stream<Long> provideProductIds() {
        return Stream.of(1L, 2L, 3L, 22L, 23L, 24L);
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
