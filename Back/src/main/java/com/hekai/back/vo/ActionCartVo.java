package com.hekai.back.vo;

import java.math.BigDecimal;
import java.util.List;

public class ActionCartVo {
	private List<ActionCartsListVo> lists;
	private BigDecimal totalPrice;	//总价格
	public List<ActionCartsListVo> getLists() {
		return lists;
	}
	public void setLists(List<ActionCartsListVo> lists) {
		this.lists = lists;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
