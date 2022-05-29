package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.OrderWithOrderItemList;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface OrderService {
    Result<List<OrderWithOrderItemList>> findOrderNoPages(Long orderNo);
}
