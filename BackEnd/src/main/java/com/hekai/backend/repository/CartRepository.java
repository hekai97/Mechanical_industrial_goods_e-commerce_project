package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(Integer userId);
    Cart findCartByProductId(Integer productId);
    void deleteByUserId(Integer userId);
    Cart findCartByProductIdAndUserId(Integer productId,Integer userId);
}