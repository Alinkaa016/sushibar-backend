package com.coursework.sushibarbackend.order.repository;

import com.coursework.sushibarbackend.order.model.Database.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository  extends JpaRepository<Status, Integer> {
}
