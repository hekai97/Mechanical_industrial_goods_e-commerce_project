package com.hekai.back.pojo;

import java.util.Date;

public class ActionAddress {
	private Integer id;
	private Integer uid;
	private String name;
	private String phone;
	private String mobile;
	private String province;
	private String city;
	private String district;
	private String addr;
	private String zip;
	private Integer default_addr;
	private Integer del_state;
	private Date created;
	private Date updated;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Integer getDefault_addr() {
		return default_addr;
	}
	public void setDefault_addr(Integer default_addr) {
		this.default_addr = default_addr;
	}
	
	public Integer getDel_state() {
		return del_state;
	}
	public void setDel_state(Integer del_state) {
		this.del_state = del_state;
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
	
}
