package com.cupitmadland.capstone;

import com.cupitmadland.capstone.entity.Role;
import com.cupitmadland.capstone.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FindByNameTest {


    private final RoleRepository roleRepository;
    @Autowired
    public FindByNameTest (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
@Test
@Transactional
public void testFindByNameTest() {

        Role expectedRole = new Role();

        expectedRole.setName("ROLE_TESTER");

        roleRepository.save(expectedRole);

        Role actualRole = roleRepository.findByName("ROLE_TESTER");

        assertEquals(expectedRole.getName(), actualRole.getName());
    }
}
