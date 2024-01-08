package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Role entities.
 * Extends JpaRepository providing basic CRUD operations.
 *
 *  @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     * @param name The name of the role to find.
     * @return The Role entity if found, null otherwise.
     */
    Role findByName(String name);
}
