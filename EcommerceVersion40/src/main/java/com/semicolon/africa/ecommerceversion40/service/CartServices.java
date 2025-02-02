package com.semicolon.africa.ecommerceversion40.service;


import com.semicolon.africa.ecommerceversion40.dto.RequestAndResponse;
import com.semicolon.africa.ecommerceversion40.exceptions.UserAlreadyExistException;
import com.semicolon.africa.ecommerceversion40.models.Item;
import com.semicolon.africa.ecommerceversion40.models.ShoppingCart;
import com.semicolon.africa.ecommerceversion40.repository.OurUserRepo;
import com.semicolon.africa.ecommerceversion40.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class CartServices {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ItemServices itemServices;
    @Autowired
    private OurUserRepo ourUserRepo;
    private static List<ShoppingCart> cartsUnUsed = List.of(new ShoppingCart(),new ShoppingCart()
    ,new ShoppingCart(),new ShoppingCart(),new ShoppingCart(),new ShoppingCart());

    public ShoppingCart assignCart(String username) {
        if(findShoppingCartByOwner(username)!=null)throw new UserAlreadyExistException(String.format("%s%d%s%s","cart",findShoppingCartByOwner(username).getId(),"has been assigned to ",username));
        Long randomCart=new SecureRandom().nextLong(0L,cartsUnUsed.size());
        shoppingCartRepository.saveAll(cartsUnUsed);
        var storeUser =ourUserRepo.findByEmail(username);
        if(storeUser.isEmpty())throw new UserAlreadyExistException("how did you get here ?");
        var yourCart=shoppingCartRepository.findShoppingCartById(randomCart);
        yourCart.setCartOwner(username);
        yourCart.setItems(List.of());
        storeUser.get().setCart(yourCart);
        ourUserRepo.save(storeUser.get());
        return shoppingCartRepository.save(yourCart);
    }

    public ShoppingCart addItemToCart(RequestAndResponse requestAndResponse){
        var cart=shoppingCartRepository.findShoppingCartByCartOwner(requestAndResponse.getStoreUser());
        Item itemToAdd=itemServices.addProductAsItem(requestAndResponse);
        cart.getItems().add(itemToAdd);
        cart.setNumberOfItems(cart.getNumberOfItems()+1);
        return shoppingCartRepository.save(cart);
    }
    public ShoppingCart removeItemFromCart(RequestAndResponse requestAndResponse){
        var itemToRemove= itemServices.findItemById(requestAndResponse.getItemId());
        var shoppingCart=shoppingCartRepository.findShoppingCartByCartOwner(requestAndResponse.getStoreUser());
        shoppingCart.getItems().remove(itemToRemove);
        shoppingCart.setNumberOfItems(shoppingCart.getNumberOfItems()-1);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart RemoveProductFromCart(RequestAndResponse requestAndResponse){
        var cart=shoppingCartRepository.findShoppingCartByCartOwner(requestAndResponse.getStoreUser());
        Item itemToAdd=itemServices.removeProductFromItem(requestAndResponse);
        cart.getItems().remove(itemToAdd);
        cart.getItems().add(itemToAdd);
        cart.setNumberOfItems(cart.getNumberOfItems()+1);
        return shoppingCartRepository.save(cart);
    }

    public List<ShoppingCart> findAllShoppingCart(){
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart findShoppingCartByOwner(String owner){
        if(ourUserRepo.findByEmail(owner).isEmpty())throw new UserAlreadyExistException("a cart was not assigned to you");
        return shoppingCartRepository.findShoppingCartByCartOwner(owner);
    }
}
