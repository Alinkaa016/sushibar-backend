package com.coursework.sushibarbackend.product.repository;

import com.onlineshop.onlineshop.OLD.Models.Database.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
