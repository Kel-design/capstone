package com.cupitmadland.capstone;

import com.cupitmadland.capstone.entity.Product;
import com.cupitmadland.capstone.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FindBySizeAndScentTest {

    private final ProductRepository productRepository;

    @Autowired
    public FindBySizeAndScentTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Test
    @Transactional
    public void testFindBySizeAndScent(){

        // Given
        Product expectedProduct = new Product();
        expectedProduct.setSize("Medium");
        expectedProduct.setScent("Rose");

        productRepository.save(expectedProduct);

        // When
        Optional<Product> actualProductOptional = productRepository.findBySizeAndScent("Medium", "Rose");

        // Then
        assertTrue(actualProductOptional.isPresent(), "Product should be present");
        Product actualProduct = actualProductOptional.get();

        assertEquals(expectedProduct.getSize(), actualProduct.getSize());
        assertEquals(expectedProduct.getScent(), actualProduct.getScent());


    }
}
