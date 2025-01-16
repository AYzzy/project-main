package com.semicolon.africa.ecommerceversion40.models;

import com.semicolon.africa.ecommerceversion40.attributeConverter.AddressAttributeConverter;
import com.semicolon.africa.ecommerceversion40.attributeConverter.CreditCardInfoAttributeConverter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BillingInformation {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long Id;
  private String phoneNumber;
  @Convert(converter = AddressAttributeConverter.class)
  private Address deliveryAddress;
  @Convert(converter = CreditCardInfoAttributeConverter.class)
  private CreditCardInfo cardInfo;
}

