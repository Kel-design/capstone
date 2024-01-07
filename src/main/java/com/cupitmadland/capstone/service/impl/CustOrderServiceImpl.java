package com.cupitmadland.capstone.service.impl;

// Initially created to handle the customer's checkout process, but transitioned to using other services instead.
import com.cupitmadland.capstone.entity.CustOrder;
import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.CustOrderService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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


    @Override
    public void saveCustOrder(CustOrder custOrder) {

    }

    @Override
    public CustOrder findCustOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<CustOrder> findAllCustOrders() {
        return null;
    }

    @Override
    public List<CustOrder> findCustOrdersByCustomer(Customer customer) {
        return null;
    }
}
