package com.hekai.backend.entites.reConstruction.compositeEntities;

import com.hekai.backend.entites.sourceEntites.Product;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class Floor {
    List<Product> oneFloor;
    List<Product> twoFloor;
    List<Product> threeFloor;
    List<Product> fourFloor;

    public List<Product> getOneFloor() {
        return oneFloor;
    }

    public void setOneFloor(List<Product> oneFloor) {
        this.oneFloor = oneFloor;
    }

    public List<Product> getTwoFloor() {
        return twoFloor;
    }

    public void setTwoFloor(List<Product> twoFloor) {
        this.twoFloor = twoFloor;
    }

    public List<Product> getThreeFloor() {
        return threeFloor;
    }

    public void setThreeFloor(List<Product> threeFloor) {
        this.threeFloor = threeFloor;
    }

    public List<Product> getFourFloor() {
        return fourFloor;
    }

    public void setFourFloor(List<Product> fourFloor) {
        this.fourFloor = fourFloor;
    }
}
