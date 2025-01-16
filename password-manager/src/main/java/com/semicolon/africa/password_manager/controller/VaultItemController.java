package com.semicolon.africa.password_manager.controller;


import com.semicolon.africa.password_manager.models.User;
import com.semicolon.africa.password_manager.models.VaultItem;
import com.semicolon.africa.password_manager.services.UserService;
import com.semicolon.africa.password_manager.services.VaultItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(allowedHeaders = "*")
public class VaultItemController {

    @Autowired
    private VaultItemService vaultItemService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody VaultItem item, @RequestParam String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        VaultItem savedItem = vaultItemService.addItem(user, item);
        return ResponseEntity.ok(savedItem);
    }

    @GetMapping
    public ResponseEntity<?> getItems(@RequestParam String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        List<VaultItem> items = vaultItemService.getItemsByUser(user);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody VaultItem item) {
        item.setId(id);
        VaultItem updatedItem = vaultItemService.updateItem(item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        vaultItemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}