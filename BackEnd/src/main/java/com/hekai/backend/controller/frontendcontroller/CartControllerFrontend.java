package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.Cart;
import com.hekai.backend.entites.reConstruction.compositeEntities.CartWithProductAndTotalPrice;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.service.CartService;
import com.hekai.backend.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/cart")
public class CartControllerFrontend {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/getcartcount.do")
    public Result<Integer> getCardCount(HttpSession httpSession){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return cartService.getCardCount(user.getId());
    }

    @RequestMapping(value = "/updatecarts.do")
    public Result<CartWithProductAndTotalPrice> updateCarts(HttpSession httpSession,Integer productId, Integer count, Integer checked){
        System.out.println("productId="+productId+"count="+count+"checked="+checked);
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return cartService.updateCarts(user,productId,count,checked);
    }

    @RequestMapping(value = "/clearcarts.do")
    public Result<Cart> clearCarts(HttpSession httpSession){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return cartService.clearCarts(user);
    }

    @RequestMapping(value = "/delcarts.do")
    public Result<CartWithProductAndTotalPrice> delCarts(HttpSession httpSession,Integer productId){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return cartService.deleteCartsProductById(user,productId);
    }

    @RequestMapping(value = "/findallcarts.do")
    public Result<CartWithProductAndTotalPrice> findAllCarts(HttpSession httpSession){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return cartService.findAllCarts(user);
    }

    @RequestMapping(value = "/savecart.do")
    public Result<Cart> saveCart(HttpSession httpSession,Integer productId,Integer count){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return cartService.saveCart(user,productId,count);
    }

}
