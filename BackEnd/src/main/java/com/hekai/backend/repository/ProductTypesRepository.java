package com.hekai.backend.repository;

import com.hekai.backend.entites.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypesRepository extends JpaRepository<ProductTypes, Integer> {
}