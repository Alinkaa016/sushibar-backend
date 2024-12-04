package com.coursework.sushibarbackend.shoppingCart.repository;

import com.coursework.sushibarbackend.shoppingCart.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
