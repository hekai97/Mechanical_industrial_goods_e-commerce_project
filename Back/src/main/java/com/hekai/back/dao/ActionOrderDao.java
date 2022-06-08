package com.hekai.back.dao;

import com.hekai.back.pojo.ActionOrder;

import java.util.List;

public interface ActionOrderDao {
	/**
	 * 根据用户id获得订单信息
	 * @param uid
	 * @return
	 */
	public List<ActionOrder> findOrderByUid(Integer uid);
	/**
	 * 前台:获取用户订单总数(各种状态下)
	 * @param userId
	 * @param status
	 * @return
	 */
	public int getTotalRecord(Integer userId, Integer status);
	/**
	 * 获取用户订单分页列表
	 * @param userId
	 * @param status
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ActionOrder> findOrders(Integer userId, Integer status, int startIndex, int pageSize);
	/**
	 * 根据用户和订单编号查询订单信息
	 * @param userId
	 * @param orderNo
	 * @return
	 */
	public ActionOrder findOrderByUserAndOrderNo(Integer userId, Long orderNo);
	/**
	 * 更新订单信息
	 * @param updateOrder
	 * @return
	 */
	public int updateOrder(ActionOrder updateOrder);
	/**
	 * 保存订单信息
	 * @param order
	 * @return
	 */
	public int insertOrder(ActionOrder order);
	
	/**
	 * 根据订单号查询订单信息
	 * @param orderNo
	 * @return
	 */
	public List<ActionOrder> searchOrders(Long orderNo);
	/**
	 * 根据订单号获得订单详情
	 * @param orderNo
	 * @return
	 */
	public ActionOrder findOrderDetailByOrderNo(Long orderNo);
}
