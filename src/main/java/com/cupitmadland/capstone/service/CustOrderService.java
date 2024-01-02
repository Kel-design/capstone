package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.CustOrder;
import com.cupitmadland.capstone.entity.Customer;

import java.util.List;

public interface CustOrderService {

    // Save a new customer order
    void saveCustOrder(CustOrder custOrder);

    // Retrieve a customer order by its ID
    CustOrder findCustOrderById(Long orderId);

    // Retrieve all customer orders
    List<CustOrder> findAllCustOrders();

    // Retrieve customer orders for a specific customer
    List<CustOrder> findCustOrdersByCustomer(Customer customer);
}
