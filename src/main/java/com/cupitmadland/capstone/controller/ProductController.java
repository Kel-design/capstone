package com.cupitmadland.capstone.controller;

import com.cupitmadland.capstone.DTO.CartItemDTO;
import com.cupitmadland.capstone.entity.CartItem;
import com.cupitmadland.capstone.entity.Product;
import com.cupitmadland.capstone.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// Used to get product details to switch size views in single product page and add items to guest shopping cart
@Controller
@SessionAttributes("cartItemDTO")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {this.productService = productService;}


    @GetMapping("/product/{productId}/details") //adding details to get size as well
    public String getProductDetails
            (@PathVariable Long productId, @RequestParam(name = "size", required = false, defaultValue = "Large")
            String size, Model model){

        //get product details based on productId
        Product product = productService.findProductById(productId);

        if(product.getSize().equals("Medium") ){
            model.addAttribute("productBaseId", productId - 1);

        }else if(product.getSize().equals("Small") ) {
            model.addAttribute("productBaseId", productId - 2);


        }else if(product.getSize().equals("Large") ) {
            model.addAttribute("productBaseId", productId);
        }

        String imageBySize = product.getSize().toLowerCase();

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProductId(product.getId());


        //pass the product details to the view
        model.addAttribute("imageBySize", imageBySize);
        model.addAttribute("product", product);
        model.addAttribute("cartItemDTO", cartItemDTO);


        return "single_product";

    }

    @PostMapping("/addToCart")
    public String addToCart(
            @ModelAttribute("cartItemDTO") CartItemDTO cartItemDTO,
            Model model,
            HttpSession session
    ){
        Product product = productService.findProductById(cartItemDTO.getProductId());

        // Create a new CartItem and set the product and quantity
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getProductQuantity());

        // Add the CartItem to the shopping cart in the session
        addToShoppingCart(session, cartItem);

        // Redirect to the shopping cart
        return "redirect:/shoppingcart/view";
    }

    private void addToShoppingCart(HttpSession session, CartItem cartItem){
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

        // If the shopping cart doesn't exist, create a new one
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }

        // Add the CartItem to the shopping cart
        cartItems.add(cartItem);
    }


    //Method to handle API endpoint to retrieve product by size AND scent and return a JSON response
    @GetMapping("/api/product/{size}/{scent}")
    @ResponseBody
    public Product getProductBySizeAndScent(@PathVariable String size, @PathVariable String scent) {
        return productService.findProductBySizeAndScent(size, scent);
    }
}
