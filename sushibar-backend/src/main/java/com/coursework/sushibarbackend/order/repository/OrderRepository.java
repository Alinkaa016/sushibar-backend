package com.coursework.sushibarbackend.order.repository;

import com.coursework.sushibarbackend.order.model.Database.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
