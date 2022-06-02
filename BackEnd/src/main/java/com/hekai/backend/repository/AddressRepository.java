package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findAddressById(Integer id);
    List<Address> findByUserId(Integer id);
}