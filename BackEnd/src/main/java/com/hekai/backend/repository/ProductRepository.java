package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByProductId(Integer id);
    List<Product> findAllByPartsId(Integer id);
    Product findProductById(Integer id);
    Page<Product> findByNameLike(String name, Pageable pageable);
    List<Product> findAllByIsHot(int hot);
    Page<Product> findByProductIdAndPartsIdAndStatusAndName(Pageable pageable,Integer productId, Integer partsId, Integer status, String name);
}