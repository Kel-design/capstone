package com.cupitmadland.capstone.controller;

import com.cupitmadland.capstone.DTO.CartItemDTO;
import com.cupitmadland.capstone.DTO.CheckoutDataDTO;
import com.cupitmadland.capstone.entity.*;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.ShoppingCartService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing shopping cart operations, including adding items, viewing the cart and processing checkout.
 * Handles interactions related to the shopping cart page and checkout process.
 */
@Controller
@RequestMapping("/shoppingcart")
@SessionAttributes({"cartItems", "cartItemDTO","customer", "payment"})
public class ShoppingCartController {

    // Autowired dependencies for the controller
    private final ShoppingCartService shoppingCartService;

    private final ProductRepository productRepository;

    private final ShoppingCartRepository shoppingCartRepository;

    private final CustomerRepository customerRepository;

    private final PaymentRepository paymentRepository;

    private final CartItemRepository cartItemRepository;

    /**
     * Constructor for ShoppingCartController.
     *
     * @param shoppingCartService       Service for shopping cart operations.
     * @param productRepository         Repository for Product entities.
     * @param shoppingCartRepository    Repository for ShoppingCart entities.
     * @param customerRepository        Repository for Customer entities.
     * @param paymentRepository         Repository for Payment entities.
     * @param cartItemRepository        Repository for CartItem entities.
     */
    @Autowired
    public  ShoppingCartController(ShoppingCartService shoppingCartService, ProductRepository productRepository,
                                   ShoppingCartRepository shoppingCartRepository, CustomerRepository customerRepository,
                                   PaymentRepository paymentRepository, CartItemRepository cartItemRepository){
        this.shoppingCartService = shoppingCartService;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * Display the shopping cart page.
     *
     * @return String representing the view name for the shopping cart page.
     */
    @GetMapping("")
    public String shoppingCart() { return "shopping_cart"; }


    /**
     * Handle the addition of items to the shopping cart.
     *
     * @param cartItemDTO   CartItemDTO containing information about the item to be added.
     * @param model         Model object for adding attributes.
     * @param session       HTTPSession for managing session attributes.
     * @return String representing the redirection to the cart view page.
     */
    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute CartItemDTO cartItemDTO, Model model, HttpSession session){

        // Retrieve the cart from the session
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

        // Perform null check and initialization if necessary
        if (cartItems == null){
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }

        CartItem newCartItem = new CartItem();
        Product product = productRepository.findById(cartItemDTO.productId).orElse(null);

        if (product != null) {
            newCartItem.setProduct(product);

            // Set other properties of newCartItem if needed
            newCartItem.setQuantity(cartItemDTO.getProductQuantity());
            newCartItem.setProductId(cartItemDTO.getProductId());
            
            // Add the newCartItem to the shopping cart
            shoppingCartService.addToCart(newCartItem);
        }
        // Retrieve and update cart data in the session
        cartItems = shoppingCartService.getCartItemList();
        session.setAttribute("cartItems", cartItems);

        return "redirect:/shoppingcart/view"; //Redirect to the cart view page
    }

    /**
     * Display the cart view page, showing the items in the shopping cart.
     *
     * @param model     Model object for adding attributes.
     * @param session   HttpSession for managing session attributes.
     * @return String representing the view name for the cart view page.
     */
    @GetMapping("/view")
    public String viewCart(Model model, HttpSession session){
        // Retrieve the cart from the session
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

        // Calculate total (which is equal to sub-total b/c tax and shipping are included)
        BigDecimal total = calculateTotal(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subTotal", total);
        model.addAttribute("total", total);
        model.addAttribute("cartItemDTO", new CartItemDTO());

        return "shopping_cart";
    }

    /**
     * Process the checkout, including handling payment and saving order details.
     *
     * @param payment           Payment entity containing payment information.
     * @param session           HttpSession for managing session attributes.
     * @param customer          Customer entity containing customer information.
     * @param model             Model object for adding attributes.
     * @param checkoutDataDTO   CheckoutDataDTO containing additional checkout information.
     * @return String representing the redirection to the order details page.
     */
    // Checkout process
    @GetMapping("/checkout")
    public String processCheckout(Payment payment,
                                  HttpSession session,
                                  @ModelAttribute Customer customer,
                                  Model model,@ModelAttribute CheckoutDataDTO checkoutDataDTO){

        // Retrieve the cart items from customer
        List<CartItem> cartItems =customer.getCartItems();

        // Set payment-specific attributes from the checkout form
        payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
        payment.setTotalAmount(calculateTotal(cartItems));
        // Set other payment-specific attributes from the checkout form

        // Set the customer associated with the payment (NEED NAME SPLITTING HERE)
        payment.setCustomer(customer);

        // Set the cart items associated with the payment
        // Loop over cartItems telling each cartItem which payment it is associated with
       for(CartItem cartItem : cartItems) {
           cartItem.setPayment(payment);

           // Setting updated stock Quantity to CartItem product when item is subtracted/purchased at Checkout
           Integer newStockQuantity = cartItem.getProduct().getStockQuantity() - cartItem.getQuantity();
           cartItem.getProduct().setStockQuantity(newStockQuantity);
           productRepository.save(cartItem.getProduct());
       }
        payment.setCartItems(cartItems);

        // Save the payment info with the customer & then re-save customer in Customer Repository
        customer.getPaymentList().add(payment);
        customerRepository.save(customer);

        // Save the payment info to the Payment Repository
        paymentRepository.save(payment);

        // Store the payment in the model
        model.addAttribute("payment", payment);

        // After saving the payment to the database
        Long orderId = payment.getId();

        return "redirect:/shoppingcart/order_details/" + payment.getId();

    }

    /**
     * Process the customer's name during the checkout process.
     *
     * @param nameOnCard        String representing the name on the payment card.
     * @param session           HttpSession for managing session attributes.
     * @param model             Model object for adding attributes.
     * @param checkoutDataDTO   CheckoutDataDTO containing additional checkout information.
     * @return String representing the redirection to the checkout page.
     */
    @PostMapping("/processCustomerName")
    public String processCustomerName(@ModelAttribute("nameOnCard") String nameOnCard,
                                      HttpSession session, Model model, @ModelAttribute CheckoutDataDTO checkoutDataDTO){

        // Extract firstName, middleName and lastName from nameOnCard
        String[] nameParts = nameOnCard.split("\\s+", 3);

        // Initialize variable for firstName, middleName and lastName
        String firstName = "";
        String middleName = "";
        String lastName = "";

        // Assign values based on the number of parts
        if (nameParts.length > 0){
            firstName = nameParts[0];
        }
        if (nameParts.length > 1){
            lastName = nameParts[1];
        }
        if (nameParts.length > 2){
            middleName = nameParts[2];
        }

        // Create a new Customer instance
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setMiddleName(middleName);
        customer.setLastName(lastName);
        customer.setAddress(checkoutDataDTO.getAddress());
        customer.setCity(checkoutDataDTO.getCity());
        customer.setState(checkoutDataDTO.getState());
        customer.setZipCode(checkoutDataDTO.getZipCode());
        customer.setPaymentList (new ArrayList<>());

        // Save customer to Customer Repository
        customer=customerRepository.save(customer);

        // Save the customer in the session
        session.setAttribute("customer", customer);
        session.setAttribute("checkoutDataDTO", checkoutDataDTO);

        // Save the customer in the model too
        model.addAttribute("customer", customer);

        // Transition the guest cart to the customer cart
        shoppingCartService.addGuestCartToCustomerCart(customer, session);

        return "redirect:/shoppingcart/checkout";
    }

    // Helper method to calculate subTotal (if needed)
    private BigDecimal calculateSubTotal(List<CartItem> cartItems){

        // Sum up all items in the cart

        return cartItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    // Helper method to calculate total
    private BigDecimal calculateTotal(List<CartItem> cartItems){

        // Sum up all items in the cart

        return cartItems.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    /**
     * Display order details for a given payment ID.
     *
     * @param paymentId Long representing the payment ID.
     * @param model     Model object for adding attributes.
     * @return String representing the view name for the order details page.
     */
    @GetMapping("/order_details/{paymentId}")
    public String viewOrderDetails(@PathVariable Long paymentId, Model model){
        // Retrieve order details from the database using paymentId
        Payment payment=paymentRepository.findById(paymentId).get();

        // Saving the payment to the model
       model.addAttribute("payment", payment);

        return "order_details";
    }
}
