package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.Order;
import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/order")
public class OrderControllerFrontend {

    @RequestMapping(value = "/confirmreceipt")
    public Result<Order> confirmReceipt(HttpSession httpSession, @RequestBody BigDecimal orderNo){
        return null;
    }

    @RequestMapping(value = "/getdetail")
    public Result<OrderAndOrderItemList> getDetail(HttpSession httpSession,@RequestBody BigDecimal orderNo){
        return null;
    }

    @RequestMapping(value = "/cancelorder")
    public Result<Order> cancelOrder(HttpSession httpSession,@RequestBody BigDecimal orderNo){
        return null;
    }

    @RequestMapping(value = "/getlist")
    public Result<PageBean<OrderAndOrderItemList>> getList(HttpSession httpSession,@RequestBody int pageNo,@RequestBody int pageSize,@RequestBody int status){
        return null;
    }

    @RequestMapping(value = "/createorder")
    public Result<OrderAndOrderItemList> createOrder(HttpSession httpSession,@RequestBody int addrId){

        return null;
    }
}
