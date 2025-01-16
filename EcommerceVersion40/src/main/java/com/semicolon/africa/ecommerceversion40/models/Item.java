package com.semicolon.africa.ecommerceversion40.models;



import com.semicolon.africa.ecommerceversion40.attributeConverter.ProductAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "store-Items")
public class Item {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Convert(converter = ProductAttributeConverter.class)
    private List<Product> products;
    private int numberOfProduct;
    private String itemName;

    @Transient
    public BigDecimal getItemPrice(){
    return products.stream().map(Product::getProductPrice).reduce(BigDecimal::add).get();
    }
    @Transient
    public String getItemName(){
        return products.stream().map(Product::getProductName).findFirst().get();
    }

}
