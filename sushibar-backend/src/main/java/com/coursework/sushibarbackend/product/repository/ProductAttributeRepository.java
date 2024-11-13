package com.coursework.sushibarbackend.product.repository;

import com.onlineshop.onlineshop.OLD.Models.Database.Product.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
}
