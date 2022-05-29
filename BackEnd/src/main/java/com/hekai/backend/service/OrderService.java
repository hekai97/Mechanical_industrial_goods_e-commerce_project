package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface OrderService {
    Result<OrderAndOrderItemList> findOrderNoPages(Long orderNo);
}
