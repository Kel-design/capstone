package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);
    Product findProductById(Long id);

    List<Product> findAllProduct();

    //New method to find a product by size AND scent
    Product findProductBySizeAndScent(String size, String scent);


}
