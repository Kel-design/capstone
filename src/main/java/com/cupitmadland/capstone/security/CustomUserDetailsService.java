package com.cupitmadland.capstone.security;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.repository.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


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
