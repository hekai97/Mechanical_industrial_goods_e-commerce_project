package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUsersById(Integer id);
    User findUsersByRoleAndAccount(Integer role,String account);
}