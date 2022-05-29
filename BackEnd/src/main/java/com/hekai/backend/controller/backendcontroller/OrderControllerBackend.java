package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.Order;
import com.hekai.backend.entites.reconstruction.OrderAndOrderItemList;
import com.hekai.backend.entites.reconstruction.PageBean;
import com.hekai.backend.entites.reconstruction.Result;
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
public class OrderControllerBackend {

    @RequestMapping(value = "/findorders_nopages")
    public Result<List<Order>> findOrdersNoPages(@RequestBody @Nullable Long orderNo){

        return null;
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
