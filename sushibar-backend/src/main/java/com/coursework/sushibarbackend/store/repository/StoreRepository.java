package com.coursework.sushibarbackend.store.repository;

import com.coursework.sushibarbackend.store.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
