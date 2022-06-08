package com.hekai.back.dao;

import com.hekai.back.pojo.ActionOrderItem;

import java.util.List;

public interface ActionOrderItemDao {
	/**
	 * 根据订单号获得订单项
	 * @param orderNo
	 * @return
	 */
	public List<ActionOrderItem> getItemsByOrderNo(Long orderNo);
	/**
	 * 批量插入订单项
	 * @param orderItems
	 */
	public int[] batchInsert(List<ActionOrderItem> orderItems);

}
