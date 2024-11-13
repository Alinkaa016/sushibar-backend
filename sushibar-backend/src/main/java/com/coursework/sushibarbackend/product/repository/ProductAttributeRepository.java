package com.coursework.sushibarbackend.product.repository;

import com.coursework.sushibarbackend.product.model.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
}
