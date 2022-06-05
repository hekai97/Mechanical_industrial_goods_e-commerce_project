package com.hekai.back.vo;

import com.hekai.back.pojo.ActionProduct;

import java.util.List;

public class ActionProductFloorVo {
	private List<ActionProduct> oneFloor;	//一楼数据
	private List<ActionProduct> twoFloor;	//二楼数据
	private List<ActionProduct> threeFloor;	//三楼数据
	private List<ActionProduct> fourFloor;	//四楼数据
	public List<ActionProduct> getOneFloor() {
		return oneFloor;
	}
	public void setOneFloor(List<ActionProduct> oneFloor) {
		this.oneFloor = oneFloor;
	}
	public List<ActionProduct> getTwoFloor() {
		return twoFloor;
	}
	public void setTwoFloor(List<ActionProduct> twoFloor) {
		this.twoFloor = twoFloor;
	}
	public List<ActionProduct> getThreeFloor() {
		return threeFloor;
	}
	public void setThreeFloor(List<ActionProduct> threeFloor) {
		this.threeFloor = threeFloor;
	}
	public List<ActionProduct> getFourFloor() {
		return fourFloor;
	}
	public void setFourFloor(List<ActionProduct> fourFloor) {
		this.fourFloor = fourFloor;
	}
	
}
