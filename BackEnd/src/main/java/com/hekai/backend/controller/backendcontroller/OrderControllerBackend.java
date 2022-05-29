package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.serviceImp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping("/mgr/order")
public class OrderControllerBackend {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @RequestMapping(value = "/findorders_nopages")
    public Result<OrderAndOrderItemList> findOrdersNoPages(@RequestBody @Nullable Long orderNo){
        return orderServiceImp.findOrderNoPages(orderNo);
    }

    @RequestMapping(value = "/search")
    public Result<PageBean<OrderAndOrderItemList>> searchOrderAndItem(@RequestBody Long orderNo, @RequestBody int pageNum, @RequestBody int pageSize){
        return null;
    }

    @RequestMapping(value = "/findorders")
    public Result<PageBean<OrderAndOrderItemList>> findOrders(@RequestBody int pageNum, @RequestBody int pageSize){

        return null;
    }

    @RequestMapping(value = "/getdetail")
    public Result<OrderAndOrderItemList> getDetail(@RequestBody Long orderNo){
        return null;
    }


}
