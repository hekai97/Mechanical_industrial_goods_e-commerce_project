package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.CartWithProductAndTotalPrice;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.Cart;
import com.hekai.backend.entites.sourceEntites.User;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface CartService {
    Result<Integer> getCardCount(int id);

    Result<CartWithProductAndTotalPrice> updateCarts(User user, Integer productId, Integer count, Integer checked);

    Result<Cart> clearCarts(User user);

    Result<CartWithProductAndTotalPrice> deleteCartsProductById(User user, Integer productId);

    Result<CartWithProductAndTotalPrice> findAllCarts(User user);

    Result<Cart> saveCart(User user, Integer productId, Integer count);
}
