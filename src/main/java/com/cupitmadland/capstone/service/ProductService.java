package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.Product;

import java.util.List;

// Used to get product information both by id and by size and scent

/**
 * Service interface for managing product-related operations.
 */
public interface ProductService {

    /**
     * Future use: Saves a product to the database.
     *
     * @param product The product to be saved.
     */
    void saveProduct(Product product);

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product to be found.
     * @return The found product or null if not found.
     */
    Product findProductById(Long id);

    /**
     * Future use: Retrieves a lise of all products.
     *
     * @return A list of all products.
     */
    List<Product> findAllProduct();


    /**
     * Finds a product by its size and scent.
     *
     * @param size The size of the product.
     * @param scent The scent of the product.
     * @return The found product or null if not found.
     */
    Product findProductBySizeAndScent(String size, String scent);

}
