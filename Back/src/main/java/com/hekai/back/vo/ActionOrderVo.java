package com.hekai.back.vo;

import java.math.BigDecimal;
import java.util.List;

public class ActionOrderVo {

	private Long orderNo;		//订单编号
	private BigDecimal amount;	//订单价格
	private Integer type;		//支付类型
	private String typeDesc;	//支付类型描述
	private Integer freight;	//邮费
	private Integer status;		//订单状态
	private String statusDesc;	//订单状态描述
	private String paymentTime;	//支付时间
	private String deliveryTime;	//邮寄时间
	private String finishTime;	//交易完成时间
	private String closeTime;	//订单关闭时间
	private String created;		//订单创建时间
	
	private List<ActionOrderItemVo>	orderItem;	//订单详情
	private Integer	addrId;		//收货地址id
	private String deliveryName;//收货人姓名
	private ActionAddressVo address;	//收货人详情
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public List<ActionOrderItemVo> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<ActionOrderItemVo> orderItem) {
		this.orderItem = orderItem;
	}
	public Integer getAddrId() {
		return addrId;
	}
	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public ActionAddressVo getAddress() {
		return address;
	}
	public void setAddress(ActionAddressVo address) {
		this.address = address;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
}
