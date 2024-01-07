package com.cupitmadland.capstone;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.entity.Role;
import com.cupitmadland.capstone.repository.CustomerRepository;
import com.cupitmadland.capstone.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class FindAllCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testFindAllCustomer() {
        // Given
        String roleName = "ROLE_USER";
        Role role = roleRepository.findByName(roleName);

        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }


        List<Customer> expectedCustomers = Arrays.asList(
                createCustomer("user1", "password1", "email1", Arrays.asList(role)),
                createCustomer("user2", "password2", "email2", Arrays.asList(role))
        );
        customerRepository.saveAll(expectedCustomers);

        // When
        List<Customer> actualCustomers = customerRepository.findAll();

        // Then
        assertEquals(expectedCustomers.size(), actualCustomers.size());

        for (int i = 0; i < expectedCustomers.size(); i++) {
            Customer expectedCustomer = expectedCustomers.get(i);
            Customer actualCustomer = actualCustomers.get(i);

            // Compare expected to actual customer Username, Password and Email
            assertEquals(expectedCustomer.getUsername(), actualCustomer.getUsername());
            assertEquals(expectedCustomer.getPassword(), actualCustomer.getPassword());
            assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
        }

    }

    private Customer createCustomer(String username, String password, String email, List<Role> roles) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));
        customer.setEmail(email);
        customer.setRoles(roles);
        return customer;
    }

}
