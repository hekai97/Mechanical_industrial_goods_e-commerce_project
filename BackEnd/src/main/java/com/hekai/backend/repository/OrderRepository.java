package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderByOrderNo(Long orderNo);
}