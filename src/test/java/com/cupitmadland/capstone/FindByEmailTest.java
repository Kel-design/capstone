package com.cupitmadland.capstone;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.repository.CustomerRepository;
import com.cupitmadland.capstone.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FindByEmailTest {



    private final CustomerRepository customerRepository;

    @Autowired
    public FindByEmailTest(CustomerRepository customerRepository){
        this.customerRepository= customerRepository;
    }


    @Test
    public void testFindByEmailTest() {
        Customer expectedCustomer = new Customer();

        expectedCustomer.setEmail("hello@gmail.com");
        expectedCustomer.setFirstName("Hello");
        expectedCustomer.setPassword("X1uZcoIh0dj");

        customerRepository.save(expectedCustomer);

        Customer actualCustomer = customerRepository.findByEmail("hello@gmail.com");

        assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());

    }
}
