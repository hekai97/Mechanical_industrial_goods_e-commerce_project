package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderByOrderNo(Long orderNo);
    Page<Order> findByUidAndStatus(Integer uid, Integer status, Pageable pageable);
}