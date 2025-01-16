package com.semicolon.africa.ecommerceversion40.models;
import com.semicolon.africa.ecommerceversion40.attributeConverter.ItemAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
@Entity
@Table(name = "carts")
public class ShoppingCart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Convert(converter = ItemAttributeConverter.class)
    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items;
    private int numberOfItems;
    private String cartOwner;
    private BigDecimal totalItemPrice;
    private String itemName;


//    @Transient
//    public BigDecimal getItemPrice(){
//        items.stream().flatMap(item ->);
//    }


}

