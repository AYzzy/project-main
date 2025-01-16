package com.semicolon.africa.ecommerceversion40.repository;

import com.semicolon.africa.ecommerceversion40.models.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
}
