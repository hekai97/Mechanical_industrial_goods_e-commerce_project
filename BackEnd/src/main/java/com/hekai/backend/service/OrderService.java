package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.compositeEntities.OrderWithOrderItemList;
import com.hekai.backend.entites.sourceEntites.User;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface OrderService {
    Result<List<OrderWithOrderItemList>> findOrderNoPages(Long orderNo);

    Result<PageBean<OrderWithOrderItemList>> searchOrderAndItem(Long orderNo, int pageNum, int pageSize);

    Result<PageBean<List<OrderWithOrderItemList>>> findOrders(int pageNum, int pageSize);

    Result<OrderWithOrderItemList> getDetail(Long orderNo);

    Result<PageBean<List<OrderWithOrderItemList>>> getOrderLists(User user, Integer status, int pageNum, int pageSize);

    Result<String> cancelOrder(User user, Long orderNo);

    Result<String> confirmReceipt(User user, Long orderNo);

    Result<OrderAndOrderItemList> creatOrder(User user, Integer addrId);
}
