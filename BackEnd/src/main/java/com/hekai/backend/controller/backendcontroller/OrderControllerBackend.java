package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.compositeEntities.OrderWithOrderItemList;
import com.hekai.backend.serviceImp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public Result<List<OrderWithOrderItemList>> findOrdersNoPages(HttpSession httpSession, @RequestBody @Nullable Long orderNo){
        return orderServiceImp.findOrderNoPages(orderNo);
    }

    @RequestMapping(value = "/search")
    public Result<PageBean<OrderWithOrderItemList>> searchOrderAndItem(HttpSession httpSession,@RequestBody Long orderNo, @RequestBody int pageNum, @RequestBody int pageSize){
        return orderServiceImp.searchOrderAndItem(orderNo,pageNum,pageSize);
    }

    @RequestMapping(value = "/findorders")
    public Result<PageBean<List<OrderWithOrderItemList>>> findOrders(HttpSession httpSession,@RequestBody int pageNum, @RequestBody int pageSize){

        return orderServiceImp.findOrders(pageNum,pageSize);
    }

    @RequestMapping(value = "/getdetail")
    public Result<OrderWithOrderItemList> getDetail(HttpSession httpSession,@RequestBody Long orderNo){
        return orderServiceImp.getDetail(orderNo);
    }


}
