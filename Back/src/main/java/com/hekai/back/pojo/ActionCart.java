package com.hekai.back.pojo;

import java.util.Date;

public class ActionCart {
	
	private Integer id;
	private Integer user_id;
	private Integer product_id;
	private Integer quantity;
	private Date created;
	private Date updated;
	private Integer checked;
	public ActionCart(Integer id, Integer user_id, Integer product_id, Integer quantity, Date created, Date updated,
			Integer checked) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.created = created;
		this.updated = updated;
		this.checked = checked;
	}
	public ActionCart() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Integer getChecked() {
		return checked;
	}
	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	
}
