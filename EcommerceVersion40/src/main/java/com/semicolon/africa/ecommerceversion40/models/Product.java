package com.semicolon.africa.ecommerceversion40.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    private String productName;
    private BigDecimal productPrice;
    private String productDescription;
    private ProductCategory productCategory;
}
