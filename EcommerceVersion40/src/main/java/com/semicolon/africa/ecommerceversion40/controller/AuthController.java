package com.semicolon.africa.ecommerceversion40.controller;


import com.semicolon.africa.ecommerceversion40.dto.RequestAndResponse;
import com.semicolon.africa.ecommerceversion40.exceptions.E_commerceExceptions;
import com.semicolon.africa.ecommerceversion40.models.Item;
import com.semicolon.africa.ecommerceversion40.service.AuthService;
import com.semicolon.africa.ecommerceversion40.service.ItemServices;
import com.semicolon.africa.ecommerceversion40.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ItemServices itemServices;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RequestAndResponse signUpRequest){
       try {
           return new ResponseEntity<>(authService.signUp(signUpRequest), CREATED);
       }catch (E_commerceExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
       }
       }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody RequestAndResponse signInRequest){
        try {
            return new ResponseEntity<>(authService.signIn(signInRequest), OK);
        }catch (E_commerceExceptions exceptions){
           return new ResponseEntity<>(exceptions.getMessage(),BAD_REQUEST);
        }
    }



    @GetMapping("/view-all-product")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok(productServices.findAllProducts());
    }


    @GetMapping("/view-all-items")
    public List<Item> getAllItems(){
        return itemServices.findAllItems();
    }


}
