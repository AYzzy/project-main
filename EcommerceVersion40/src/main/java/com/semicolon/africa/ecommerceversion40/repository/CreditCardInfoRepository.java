package com.semicolon.africa.ecommerceversion40.repository;

import com.semicolon.africa.ecommerceversion40.models.CardType;
import com.semicolon.africa.ecommerceversion40.models.CreditCardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo,Long> {
     CreditCardInfo findCreditCardInfoByCard(CardType cardType);
     CreditCardInfo findCreditCardInfoByCardCvv(String cvv);
     CreditCardInfo findCreditCardInfoByNameOnCard(String nameOnCard);
}
