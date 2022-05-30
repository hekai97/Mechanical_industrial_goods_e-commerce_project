package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrderId(Long orderNo);
    Page<OrderItem> findByOrderId(Long orderNo, Pageable pageable);
}