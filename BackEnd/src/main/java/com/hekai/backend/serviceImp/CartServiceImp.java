package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.CartWithProduct;
import com.hekai.backend.entites.reConstruction.compositeEntities.CartWithProductAndTotalPrice;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.Cart;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.repository.CartRepository;
import com.hekai.backend.repository.ProductRepository;
import com.hekai.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Result<Integer> getCardCount(int id) {
        return Result.createRespBySuccess(cartRepository.findByUserId(id).size());
    }

    @Override
    public Result<CartWithProductAndTotalPrice> updateCarts(User user, Integer productId, Integer count, Integer checked) {
        CartWithProductAndTotalPrice cartWithProductAndTotalPrice=new CartWithProductAndTotalPrice();
        List<CartWithProduct> cartWithProducts=new ArrayList<>();
        List<Cart> carts=cartRepository.findByUserId(user.getId());
        for(Cart cart:carts){
            CartWithProduct cartWithProduct;
            if(cart.getProductId()==productId){
                cart.setQuantity(count);
                cart.setUpdated(new Timestamp(new Date().getTime()));
                cartWithProduct = new CartWithProduct(cart, user, productRepository.findProductById(productId), checked);
            }
            else {
                 cartWithProduct = new CartWithProduct(cart, user, productRepository.findProductById(productId), 0);
            }
            cartWithProducts.add(cartWithProduct);
        }
        cartWithProductAndTotalPrice.setList(cartWithProducts);
        cartWithProductAndTotalPrice.generationTotalPrice();
        return Result.createRespBySuccess(cartWithProductAndTotalPrice);
    }

    @Override
    public Result<Cart> clearCarts(User user) {
        cartRepository.deleteByUserId(user.getId());
        return Result.createRespBySuccessMessage("成功清空购物车！");
    }

    @Override
    public Result<CartWithProductAndTotalPrice> deleteCartsProductById(User user, Integer productId) {
        Cart cart= cartRepository.findCartByProductIdAndUserId(productId,user.getId());
        if(cart==null){
            return Result.createByErrorMessage("商品删除失败");
        }
        cartRepository.delete(cart);
        //TODO
        return findAllCarts(user);
    }

    @Override
    public Result<CartWithProductAndTotalPrice> findAllCarts(User user) {
        List<Cart> carts=cartRepository.findByUserId(user.getId());
        List<CartWithProduct> cartWithProducts=new ArrayList<>();
        for(Cart cart:carts){
            CartWithProduct cartWithProduct=new CartWithProduct(cart,user,productRepository.findProductById(cart.getProductId()),0);
            cartWithProducts.add(cartWithProduct);
        }
        CartWithProductAndTotalPrice cartWithProductAndTotalPrice=new CartWithProductAndTotalPrice();
        cartWithProductAndTotalPrice.setList(cartWithProducts);
        cartWithProductAndTotalPrice.generationTotalPrice();
        return Result.createRespBySuccess(cartWithProductAndTotalPrice);
    }

    @Override
    public Result<Cart> saveCart(User user, Integer productId, Integer count) {
        Cart cart=new Cart();
        cart.setProductId(productId);
        cart.setQuantity(count);
        cart.setUserId(user.getId());
        cart.setCreated(new Timestamp(new Date().getTime()));
        cart.setUpdated(new Timestamp(new Date().getTime()));
        cartRepository.save(cart);
        return Result.createRespBySuccessMessage("商品已成功加入购物车");
    }
}
