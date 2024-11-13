package com.coursework.sushibarbackend.product.repository;

import com.coursework.sushibarbackend.product.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
