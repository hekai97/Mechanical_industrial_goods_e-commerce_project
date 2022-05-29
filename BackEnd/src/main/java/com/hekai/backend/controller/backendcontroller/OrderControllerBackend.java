package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.OrderWithOrderItemList;
import com.hekai.backend.serviceImp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OrderServiceImp orderServiceImp;

    @RequestMapping(value = "/findorders_nopages")
    public Result<List<OrderWithOrderItemList>> findOrdersNoPages(@RequestBody @Nullable Long orderNo){
        return orderServiceImp.findOrderNoPages(orderNo);
    }

    //TODO 继续写
    @RequestMapping(value = "/search")
    public Result<PageBean<OrderWithOrderItemList>> searchOrderAndItem(@RequestBody Long orderNo, @RequestBody int pageNum, @RequestBody int pageSize){
        return null;
    }

    @RequestMapping(value = "/findorders")
    public Result<PageBean<OrderWithOrderItemList>> findOrders(@RequestBody int pageNum, @RequestBody int pageSize){

        return null;
    }

    @RequestMapping(value = "/getdetail")
    public Result<OrderWithOrderItemList> getDetail(@RequestBody Long orderNo){
        return null;
    }


}
