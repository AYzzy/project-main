package com.semicolon.africa.ecommerceversion40.service;


import com.semicolon.africa.ecommerceversion40.dto.RequestAndResponse;
import com.semicolon.africa.ecommerceversion40.exceptions.E_commerceExceptions;
import com.semicolon.africa.ecommerceversion40.exceptions.UserAlreadyExistException;
import com.semicolon.africa.ecommerceversion40.models.Item;
import com.semicolon.africa.ecommerceversion40.models.OurUsers;
import com.semicolon.africa.ecommerceversion40.models.Product;
import com.semicolon.africa.ecommerceversion40.models.ShoppingCart;
import com.semicolon.africa.ecommerceversion40.repository.OurUserRepo;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private OurUserRepo ourUserRepo;
//   @Autowired
//    private EmailServicesImpl emailServices;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private ProductServices productServices;
    @Autowired
    private CartServices cartServices;
    @Autowired
    private ItemServices itemServices;
    public RequestAndResponse signUp(RequestAndResponse registrationRequest){
        RequestAndResponse resp = new RequestAndResponse();
        if(!EmailValidator.getInstance().isValid(registrationRequest.getEmail()))
            throw new E_commerceExceptions("something went wrong");
        if(ourUserRepo.findByEmail(registrationRequest.getEmail()).isPresent())throw new UserAlreadyExistException("user already exists");
        OurUsers ourUsers = new OurUsers();
        ourUsers.setRole(registrationRequest.getRole());
        ourUsers.setEmail(registrationRequest.getEmail());
        ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        ourUsers.setUsername(registrationRequest.getStoreUser());
        OurUsers ourUserResult = ourUserRepo.save(ourUsers);
        //emailServices.sendHtmlEmail(ourUserResult.getUsername(), ourUserResult.getEmail(),ourUserResult.getEmail());
        resp.setMessage(String.format("%s%s",ourUserResult.getEmail(),"  successfully registered" ));
        resp.setStatusCode(201);
        return resp;
    }

    public RequestAndResponse signIn(RequestAndResponse signinRequest){
        RequestAndResponse response = new RequestAndResponse();
        var user = ourUserRepo.findByEmail(signinRequest.getEmail());
        if(user.isEmpty())throw new UserAlreadyExistException("user not found");
        if(!passwordEncoder.matches(signinRequest.getPassword(),user.get().getPassword()))throw new UserAlreadyExistException("something went wrong");
        response.setStatusCode(200);
        response.setMessage("successfully logged in");
        response.setStoreUser(user.get().getEmail());
        return response;
    }


    public RequestAndResponse addProduct(RequestAndResponse productRequest){
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductCategory(productRequest.getProductCategory());
        product.setProductPrice(BigDecimal.valueOf(productRequest.getProductPrice()));
        product.setProductDescription(productRequest.getProductDescription());
        var productResponse =productServices.addProduct(product);
        productResponse.setStatusCode(200);
        return productResponse;
    }


    public ShoppingCart addProductToCart(RequestAndResponse requestAndResponse){
        if(cartServices.findShoppingCartByOwner(requestAndResponse.getStoreUser())==null)
            throw new UserAlreadyExistException("you are not a user");
        return cartServices.addItemToCart(requestAndResponse);
    }


    public ShoppingCart removeItemFromCart(RequestAndResponse requestAndResponse){
        if(cartServices.findShoppingCartByOwner(requestAndResponse.getStoreUser())==null)
            throw new UserAlreadyExistException("you are not a user");
        return cartServices.removeItemFromCart(requestAndResponse);
    }

    public ShoppingCart removeProductFromCart(RequestAndResponse requestAndResponse){
        if(cartServices.findShoppingCartByOwner(requestAndResponse.getStoreUser())==null)
            throw new UserAlreadyExistException("you are not a user");
        return cartServices.RemoveProductFromCart(requestAndResponse);
    }

    public ShoppingCart assignCartToUser(String username){
         return cartServices.assignCart(username);
    }

    public List<ShoppingCart> findAllCarts() {
        return cartServices.findAllShoppingCart();
    }

    public Item addProductAsItem(RequestAndResponse requestAndResponse){
        return itemServices.addProductAsItem(requestAndResponse);
    }

    public ShoppingCart findCartByUserName(String userName){
        return cartServices.findShoppingCartByOwner(userName);
    }



    public Product findProductById(UUID id){
        return productServices.findProductsById(id);
    }



}
