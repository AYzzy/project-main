package com.semicolon.africa.password_manager.services;

import com.semicolon.africa.password_manager.models.User;
import com.semicolon.africa.password_manager.models.VaultItem;
import com.semicolon.africa.password_manager.repository.VaultItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaultItemService {

    @Autowired
    private VaultItemRepository vaultItemRepository;

    public VaultItem addItem(User user, VaultItem item) {
        item.setUser(user);
        return vaultItemRepository.save(item);
    }

    public List<VaultItem> getItemsByUser(User user) {
        return vaultItemRepository.findByUserId(user.getId());
    }

    public VaultItem updateItem(VaultItem item) {
        return vaultItemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        vaultItemRepository.deleteById(itemId);
    }
}