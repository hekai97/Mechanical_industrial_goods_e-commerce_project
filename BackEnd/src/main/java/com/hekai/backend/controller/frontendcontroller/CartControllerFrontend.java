package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.Cart;
import com.hekai.backend.entites.reConstruction.compositeEntities.CartWithProductAndTotalPrice;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
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

    @RequestMapping(value = "/getcartcount")
    public Result<Integer> getCardCount(HttpSession httpSession, @RequestBody int id){
        return null;
    }

    @RequestMapping(value = "/updatecarts")
    public Result<CartWithProductAndTotalPrice> updateCarts(HttpSession httpSession,@RequestBody int productId, @RequestBody int count, @RequestBody boolean checked){
        return null;
    }

    @RequestMapping(value = "/clearcarts")
    public Result<Cart> clearCarts(HttpSession httpSession,@RequestBody int userId){
        //个人认为需要加一个用户id
        return null;
    }

    @RequestMapping(value = "/delcarts")
    public Result<CartWithProductAndTotalPrice> delCarts(HttpSession httpSession,@RequestBody int userId, @RequestBody int productId){
        return null;
    }

    @RequestMapping(value = "/findallcarts")
    public Result<CartWithProductAndTotalPrice> findAllCarts(HttpSession httpSession,@RequestBody int userId){

        return null;
    }

    @RequestMapping(value = "/savecart")
    public Result<Cart> saveCart(HttpSession httpSession,@RequestBody int userId,@RequestBody int productId,@RequestBody int count){
        return null;
    }

}
