package com.semicolon.africa.ecommerceversion40.repository;



import com.semicolon.africa.ecommerceversion40.models.Product;
import com.semicolon.africa.ecommerceversion40.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductByProductCategory(ProductCategory productCategory);
    Product findProductByProductPrice(BigDecimal price);
    Product findProductByProductName(String name);
    Product findProductByProductDescription(String Description);
    Product findProductById(UUID id);
}
