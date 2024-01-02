package com.cupitmadland.capstone.service.impl;

import com.cupitmadland.capstone.entity.Product;
import com.cupitmadland.capstone.exception.ProductNotFoundException;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.ProductService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private CustomerRepository customerRepository;

    private CustOrderRepository custOrderRepository;

    private PaymentRepository paymentRepository;

    private ProductRepository productRepository;

    private RoleRepository roleRepository;

    private ShoppingCartRepository shoppingCartRepository;

    private PasswordEncoder passwordEncoder;

    public ProductServiceImpl(CustomerRepository customerRepository, CustOrderRepository custOrderRepository, PaymentRepository paymentRepository,
                              ProductRepository productRepository, RoleRepository roleRepository, ShoppingCartRepository shoppingCartRepository,
                              PasswordEncoder passwordEncoder) {
        super();
        this.customerRepository = customerRepository;
        this.custOrderRepository = custOrderRepository;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // Saves a product to the database.
    @Override
    public void saveProduct(Product product) {
    if (product == null){
        throw new IllegalArgumentException("Invalid product");
    }
    // Save the product
        productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        //checking if product exists
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }else{
            //Handle if product with given ID is not found
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product findProductBySizeAndScent(String size, String scent) {
        //Using the findBySize method from the productRepository
        return productRepository.findBySizeAndScent(size, scent).orElse(null);
    }
}
