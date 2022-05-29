package com.hekai.backend.controller;

import com.hekai.backend.entites.Order;
import com.hekai.backend.entites.reconstruction.OrderAndOrderItem;
import com.hekai.backend.entites.reconstruction.PageBean;
import com.hekai.backend.entites.reconstruction.Result;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping("/mgr/order")
public class OrderController {

    @RequestMapping(value = "/findorders_nopages")
    public Result<List<Order>> findOrdersNoPages(@RequestBody @Nullable Long orderNo){

        return null;
    }

    @RequestMapping(value = "/search")
    public Result<PageBean<OrderAndOrderItem>> searchOrderAndItem(@RequestBody Long orderNo,@RequestBody int pageNum,@RequestBody int pageSize){
        return null;
    }

    @RequestMapping(value = "findorders")
    public Result<PageBean<OrderAndOrderItem>> findOrders(@RequestBody int pageNum,@RequestBody int pageSize){

        return null;
    }

    @RequestMapping(value = "getdetail")
    public Result<OrderAndOrderItem> getDetail(@RequestBody Long orderNo){
        return null;
    }

}
