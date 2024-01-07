package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


// Entity do hold USER role for security check in registration/login
@Entity
@Data
@NoArgsConstructor
@Table
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

        @Column(nullable = false, unique = true)
    String name;

    //Creating a @ManyToMany entity relationship for Customer to Role using customer_id and role_id
        @ManyToMany(mappedBy = "roles")
        List<Customer> customerList = new ArrayList<>();
}
