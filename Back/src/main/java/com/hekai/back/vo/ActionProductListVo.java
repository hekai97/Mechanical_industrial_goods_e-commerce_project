package com.hekai.back.vo;

import java.math.BigDecimal;

public class ActionProductListVo {
	private Integer id;
	private String name;
	private BigDecimal price;
	private Integer status;//上下架
	private String statusDesc;//上下架描述文字
	private String productCategory;
	private String partsCategory;
	private String iconUrl;
	private Integer is_hot;
	private String hotStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getPartsCategory() {
		return partsCategory;
	}
	public void setPartsCategory(String partsCategory) {
		this.partsCategory = partsCategory;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public String getHotStatus() {
		return hotStatus;
	}
	public void setHotStatus(String hotStatus) {
		this.hotStatus = hotStatus;
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
	
}
