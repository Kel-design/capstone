package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Role, used for security checks in registration/login
 */
@Entity
@Data
@NoArgsConstructor
@Table
public class Role {

    /**
     * The unique identifier for the Role.
     */
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * The name of the Role.
     */
        @Column(nullable = false, unique = true)
    String name;

    /**
     * The list of Customers associated with the Role.
     */
        @ManyToMany(mappedBy = "roles")
        List<Customer> customerList = new ArrayList<>();
}
