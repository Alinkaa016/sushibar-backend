package com.coursework.sushibarbackend.product.repository;

import com.coursework.sushibarbackend.product.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(int productId);
}
