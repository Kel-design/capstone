package com.cupitmadland.capstone.security;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.repository.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 * Used to process user registration and login.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;

    /**
     * Constructs a new CustomUserDetailsService.
     *
     * @param customerRepository The repository for accessing customer-related data.
     */
    public CustomUserDetailsService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    /**
     * Load user details by username (email).
     * @param email The email (username) of the user.
     * @return UserDetails containing user information.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(email);
        if(customer != null){
            return new org.springframework.security.core.userdetails.User(customer.getUsername(),
                    customer.getPassword(),
                    customer.getRoles().stream()
                            .map((role -> new SimpleGrantedAuthority(role.getName())))
                                    .collect(Collectors.toList()));

        }else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
