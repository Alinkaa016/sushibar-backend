package com.coursework.sushibarbackend.store.repository;

import com.coursework.sushibarbackend.store.model.entity.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepository extends JpaRepository<StoreItem, Integer> {
}
