package com.coursework.sushibarbackend.order.repository;

import com.coursework.sushibarbackend.order.model.Database.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
