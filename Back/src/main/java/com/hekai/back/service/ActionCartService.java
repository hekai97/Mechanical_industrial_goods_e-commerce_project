package com.hekai.back.service;

import com.hekai.back.common.SverResponse;
import com.hekai.back.vo.ActionCartVo;

public interface ActionCartService {
	/**
	 * 保存商品信息到购物车
	 * @param userId
	 * @param productId
	 * @param count
	 * @return
	 */
	public SverResponse<String> saveOrUpdate(Integer userId, Integer productId, Integer count);
	/**
	 * 查询用户购物车中商品信息
	 * @param userId
	 * @return
	 */
	public SverResponse<ActionCartVo> findAllCarts(Integer userId);
	/**
	 * 清空购物车
	 * @param userId
	 * @return
	 */
	public SverResponse<String> clearCart(Integer userId);
	/**
	 * 更新购物车中的商品数量
	 * @param userId
	 * @param productId
	 * @param count
	 * @param checked
	 * @return
	 */
	public SverResponse<ActionCartVo> updateCart(Integer userId, Integer productId, Integer count, Integer checked);
	/**
	 * 删除购物车中的某条商品
	 * @param userId
	 * @param productId
	 * @return
	 */
	public SverResponse<ActionCartVo> deleteCart(Integer userId, Integer productId);
	/**
	 * 获取登录用户购物车中商品的个数
	 * @param userId
	 * @return
	 */
	public SverResponse<Integer> getCartsCount(Integer userId);

}
