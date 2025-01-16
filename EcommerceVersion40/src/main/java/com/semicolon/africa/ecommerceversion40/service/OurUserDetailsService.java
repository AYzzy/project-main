package com.semicolon.africa.ecommerceversion40.service;

import com.semicolon.africa.ecommerceversion40.exceptions.UserAlreadyExistException;
import com.semicolon.africa.ecommerceversion40.models.OurUsers;
import com.semicolon.africa.ecommerceversion40.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OurUserDetailsService {

    @Autowired
    private OurUserRepo ourUserRepo;

    public OurUsers loadUserByUsername(String username) throws UsernameNotFoundException {
        return ourUserRepo.findByEmail(username).orElseThrow(()-> new UserAlreadyExistException("user not found"));
    }
}
