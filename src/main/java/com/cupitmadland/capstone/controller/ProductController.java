package com.cupitmadland.capstone.controller;

import com.cupitmadland.capstone.entity.Product;
import com.cupitmadland.capstone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {this.productService = productService;}


    @GetMapping("/product/{productId}/details") //adding details to get size as well
    public String getProductDetails
            (@PathVariable Long productId, @RequestParam(name = "size", required = false, defaultValue = "Large")
            String size, Model model){

        //get product details based on productId
        Product product = productService.findProductById(productId);

        //pass the product details to the view
        model.addAttribute("product", product);
        model.addAttribute("size", size);

        return "single_product";


    }
}
