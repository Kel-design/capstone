package com.cupitmadland.capstone.service.impl;


import com.cupitmadland.capstone.entity.CustOrder;
import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.CustOrderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Initially created to handle the Customer checkout process, but transitioned to other Services.
 * Keeping for FUTURE USE to help with improvements in separating shopping cart and checkout process.
 */
@Service
public class CustOrderServiceImpl implements CustOrderService {

    private CustomerRepository customerRepository;

    private CustOrderRepository custOrderRepository;

    private PaymentRepository paymentRepository;

    private ProductRepository productRepository;

    private RoleRepository roleRepository;

    private ShoppingCartRepository shoppingCartRepository;

    private PasswordEncoder passwordEncoder;

    public CustOrderServiceImpl(CustomerRepository customerRepository, CustOrderRepository custOrderRepository, PaymentRepository paymentRepository,
                               ProductRepository productRepository, RoleRepository roleRepository, ShoppingCartRepository shoppingCartRepository,
                               PasswordEncoder passwordEncoder){
        super();
        this.customerRepository = customerRepository;
        this.custOrderRepository = custOrderRepository;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a customer order (CustOrder).
     *
     * @param custOrder The customer order to be saved.
     */
    @Override
    public void saveCustOrder(CustOrder custOrder) {

    }

    /**
     * FUTURE USE: Find a customer order by its ID.
     *
     * @param orderId The Id of the customer order to be found.
     * @return The customer order with the specified ID.
     */
    @Override
    public CustOrder findCustOrderById(Long orderId) {
        return null;
    }

    /**
     * FUTURE USE: Finds all customer orders.
     *
     * @return A list of all customer order.
     */
    @Override
    public List<CustOrder> findAllCustOrders() {
        return null;
    }

    /**
     * FUTURE USE: Finds customer orders associated with a specific customer.
     * @param customer The customer for whom to find orders.
     * @return A list of customer orders associated with the specified customer.
     */
    @Override
    public List<CustOrder> findCustOrdersByCustomer(Customer customer) {
        return null;
    }
}
