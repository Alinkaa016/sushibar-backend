package com.coursework.sushibarbackend.order.repository;

import com.coursework.sushibarbackend.order.model.Database.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Integer> {
}
