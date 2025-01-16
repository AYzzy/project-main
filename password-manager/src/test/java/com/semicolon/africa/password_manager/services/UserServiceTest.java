package com.semicolon.africa.password_manager.services;

import com.semicolon.africa.password_manager.models.User;
import com.semicolon.africa.password_manager.repository.UserRepository;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void registerUser() {
        User user = new User();
        user.setUsername("ayomide");
        user.setPassword("1234");
        userRepository.save(user);
        assertEquals(1,userRepository.count());
    }

    @Test
    void findByUsername() {
    }

    @Test
    void authenticate() {
    }
}