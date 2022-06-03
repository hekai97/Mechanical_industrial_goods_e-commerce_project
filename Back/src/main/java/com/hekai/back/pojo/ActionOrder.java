package com.hekai.back.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ActionOrder {
	private Integer id;
	private Long order_no;
	private Integer uid;
	private Integer addr_id;
	private BigDecimal amount;
	private Integer type;
	private Integer freight;
	private Integer status;
	private Date payment_time;
	private Date delivery_time;
	private Date finish_time;
	private Date close_time;
	private Date updated;
	private Date created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(Integer addr_id) {
		this.addr_id = addr_id;
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
	public Date getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(Date payment_time) {
		this.payment_time = payment_time;
	}
	public Date getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}
	public Date getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	public Date getClose_time() {
		return close_time;
	}
	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
