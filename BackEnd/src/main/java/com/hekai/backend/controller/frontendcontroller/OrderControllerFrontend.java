package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.OrderWithOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.service.OrderService;
import com.hekai.backend.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/order")
public class OrderControllerFrontend {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/confirmreceipt.do",method = RequestMethod.POST)
    public Result<String> confirmReceipt(HttpSession httpSession, Long orderNo){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return orderService.confirmReceipt(user,orderNo);
    }

    @RequestMapping(value = "/getdetail.do",method = RequestMethod.GET)
    public Result<OrderWithOrderItemList> getDetail(HttpSession httpSession,Long orderNo){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return orderService.getDetail(orderNo);
    }

    @RequestMapping(value = "/cancelorder.do",method = RequestMethod.POST)
    public Result<String> cancelOrder(HttpSession httpSession,Long orderNo){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return orderService.cancelOrder(user,orderNo);
    }

    @RequestMapping(value = "/getlist.do",method = RequestMethod.GET)
    public Result<PageBean<List<OrderWithOrderItemList>>> getList(HttpSession httpSession, Integer status,
                                                                  @RequestParam(value="pageNum",defaultValue="1") int pageNum,
                                                                  @RequestParam(value="pageSize",defaultValue="10") int pageSize){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return orderService.getOrderLists(user,status,pageNum,pageSize);
    }

    @RequestMapping(value = "/createorder.do",method = RequestMethod.POST)
    public Result<OrderAndOrderItemList> createOrder(HttpSession httpSession,Integer addrId){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return orderService.creatOrder(user,addrId);
    }
}
