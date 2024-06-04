package com.capstone.CapstoneServer.entities;

import java.util.Date;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private RepoUser userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {
        User user = new User.UserBuilder()
                .userName("testuser")
                .email("test@email.com")
                .password("password")
                .dateOfCreation(new Date())
                .build();

                userRepository.save(user);
        assertThat(user.getUserId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUserByNameTest() {
        User user = userRepository.findByUserName("testuser");
        assertThat(user.getUserName()).isEqualTo("testuser");

    }


    @Test
    @Order(3)
    public void getUserByEmailTest() {
        User user = userRepository.findByEmail("test@email.com");
        assertThat(user.getEmail()).isEqualTo("test@email.com");

    }
    @Test
    @Order(4)
    public void getUserByNonExistentNameTest() {
        // username that does not exist
        User user = userRepository.findByUserName("nonexistentuser");
        assertNull(user);
    }
    @Test
    @Order(5)
    public void getUserByNonExistentEmailTest() {
        // user with an email that does not exist
        User user = userRepository.findByEmail("nonexistent@email.com");
        assertNull(user);
    }

}