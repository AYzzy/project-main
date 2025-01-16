package com.semicolon.africa.password_manager.repository;

import com.semicolon.africa.password_manager.models.VaultItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaultItemRepository extends JpaRepository<VaultItem, Long> {
    List<VaultItem> findByUserId(Long userId);
}


