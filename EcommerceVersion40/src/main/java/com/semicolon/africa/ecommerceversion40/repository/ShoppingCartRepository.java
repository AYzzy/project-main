package com.semicolon.africa.ecommerceversion40.repository;


import com.semicolon.africa.ecommerceversion40.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findShoppingCartByNumberOfItems(int numberOfItems);

    ShoppingCart findShoppingCartById(Long id);

    ShoppingCart findShoppingCartByCartOwner(String owner);
}
