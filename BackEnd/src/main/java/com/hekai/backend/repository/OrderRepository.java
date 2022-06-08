package com.hekai.backend.repository;

import com.hekai.backend.entites.sourceEntites.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderByOrderNo(Long orderNo);
    Page<Order> findOrdersByUidAndStatus(Integer uid, Integer status, Pageable pageable);

    @Query(nativeQuery = true,value = "select  * from mydatabase.order where uid=:uid and status=:status limit :startIndex,:pageSize")
    List<Order> findOrdersByUidAndStatus(@Param(value = "uid") Integer uid,
                                         @Param(value = "status") Integer status,
                                         @Param(value = "startIndex") Integer startIndex,
                                         @Param(value = "pageSize") Integer pageSize);
    @Query(nativeQuery = true,value = "select count(*) from mydatabase.order where uid=:uid and status=:status limit :startIndex,:pageSize")
    Integer findTotalOrderCount(@Param(value = "uid") Integer uid,
                                @Param(value = "status") Integer status,
                                @Param(value = "startIndex") Integer startIndex,
                                @Param(value = "pageSize") Integer pageSize);
}