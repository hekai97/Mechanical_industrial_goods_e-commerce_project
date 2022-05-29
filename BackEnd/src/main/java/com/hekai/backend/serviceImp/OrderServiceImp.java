package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.repository.OrderItemRepository;
import com.hekai.backend.repository.OrderRepository;
import com.hekai.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Result<OrderAndOrderItemList> findOrderNoPages(Long orderNo) {

        return null;
    }
}
