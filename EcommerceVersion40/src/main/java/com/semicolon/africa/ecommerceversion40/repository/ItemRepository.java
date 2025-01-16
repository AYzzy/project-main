package com.semicolon.africa.ecommerceversion40.repository;


import com.semicolon.africa.ecommerceversion40.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findItemById(Long id);
    Item findItemByNumberOfProduct(int numberOfProduct);

}
