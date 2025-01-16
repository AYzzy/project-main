package com.semicolon.africa.ecommerceversion40.models;

import com.semicolon.africa.ecommerceversion40.attributeConverter.ShoppingCartConverter;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "ourusers")
public class OurUsers{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String username;
    private String role;
    @Convert(converter = ShoppingCartConverter.class)
    private ShoppingCart cart;
}