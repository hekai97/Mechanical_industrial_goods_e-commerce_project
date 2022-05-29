package com.hekai.backend.controller.frontendcontriller;

import com.hekai.backend.entites.Order;
import com.hekai.backend.entites.reconstruction.OrderAndOrderItemList;
import com.hekai.backend.entites.reconstruction.PageBean;
import com.hekai.backend.entites.reconstruction.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/order")
public class OrderControllerFrontend {

    @RequestMapping(value = "/confirmreceipt")
    public Result<Order> confirmReceipt(@RequestBody BigDecimal orderNo){
        return null;
    }

    @RequestMapping(value = "/getdetail")
    public Result<OrderAndOrderItemList> getDetail(@RequestBody BigDecimal orderNo){
        return null;
    }

    @RequestMapping(value = "/cancelorder")
    public Result<Order> cancelOrder(@RequestBody BigDecimal orderNo){
        return null;
    }

    @RequestMapping(value = "/getlist")
    public Result<PageBean<OrderAndOrderItemList>> getList(@RequestBody int pageNo,@RequestBody int pageSize,@RequestBody int status){
        return null;
    }

    @RequestMapping(value = "/createorder")
    public Result<OrderAndOrderItemList> createOrder(@RequestBody int addrId){

        return null;
    }
}
