package com.hekai.back.dao;

import com.hekai.back.pojo.ActionCart;

import java.util.List;

public interface ActionCartDao {
	/**
	 * 根据用户id和产品id查询购物车
	 * @param userId
	 * @param productId
	 * @return
	 */
	public ActionCart findCartByUserAndProductId(Integer userId, Integer productId);
	/**
	 * 新增购物车记录
	 * @param cart
	 */
	public Integer insertCart(ActionCart cart);
	/**
	 * 更新购物车中的商品数量
	 * @param actionCart
	 * @return
	 */
	public Integer updateCartById(ActionCart actionCart);
	/**
	 * 根据用户id查找购物车中商品信息
	 * @param userId
	 * @return
	 */
	public List<ActionCart> findCartByUser(Integer userId);
	/**
	 * 删除某个用户购物车中的所有商品
	 * @param userId
	 * @return
	 */
	public int deleteCartByUserId(Integer userId);
	/**
	 * 更新购物车中的商品数量
	 * @param actionCart
	 * @return
	 */
	public int updateCartByUserIdAndProductId(ActionCart actionCart);
	/**
	 * 删除购物车中的某条商品信息
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int deleteCartByUserIdAndProductId(Integer userId, Integer productId);
	/**
	 * 获取当前用户购物车中的商品数量
	 * @param userId
	 * @return
	 */
	public int getCartCountByUserId(Integer userId);

}
