package com.hekai.back.vo;

import java.math.BigDecimal;

public class ActionOrderItemVo {
	
	private Long orderNo;
	private Integer goodsId;
	private String goodsName;
	private String iconUrl;
	private BigDecimal curPrice;
	private Integer quantity;
	private BigDecimal totalPrice;
	private String created;
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public BigDecimal getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(BigDecimal curPrice) {
		this.curPrice = curPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
}
