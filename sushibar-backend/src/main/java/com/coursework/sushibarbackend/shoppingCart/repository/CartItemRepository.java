package com.coursework.sushibarbackend.shoppingCart.repository;

import com.coursework.sushibarbackend.shoppingCart.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
