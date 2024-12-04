package com.coursework.sushibarbackend.order.repository;

import com.coursework.sushibarbackend.order.model.Database.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
