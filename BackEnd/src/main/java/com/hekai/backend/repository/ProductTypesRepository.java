package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypesRepository extends JpaRepository<ProductTypes, Integer> {
    ProductTypes findProductTypesById(Integer id);
    List<ProductTypes> findAllByParentId(Integer id);
    List<ProductTypes> findProductTypesByLevel(Integer id);
}