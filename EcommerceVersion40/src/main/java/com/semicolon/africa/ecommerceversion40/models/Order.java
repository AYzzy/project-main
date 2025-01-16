package com.semicolon.africa.ecommerceversion40.models;

import com.semicolon.africa.ecommerceversion40.attributeConverter.ShoppingCartConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store-orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String customerUserName;
    private Integer numberOfItem;
    private String productName;
    private BigDecimal priceOfItem;
    private BigDecimal totalPriceOfItem;
    @Convert(converter = ShoppingCartConverter.class)
    private ShoppingCart shoppingCart;

//    
//    public Order (ShoppingCart shoppingCart){
//              for(var item : shoppingCart.getItems()){
//                     new Order()
//              }
//
//
//
//    }



}
