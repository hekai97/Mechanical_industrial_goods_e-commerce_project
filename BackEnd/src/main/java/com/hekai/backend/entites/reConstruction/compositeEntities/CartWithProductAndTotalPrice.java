package com.hekai.backend.entites.reConstruction.compositeEntities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class CartWithProductAndTotalPrice {
    private List<CartWithProduct> list=new ArrayList<>();
    private BigDecimal totalPrice=new BigDecimal(0);

    public List<CartWithProduct> getList() {
        return list;
    }

    public void setList(List<CartWithProduct> list) {
        this.list = list;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void generationTotalPrice(){
        for(CartWithProduct cartWithProduct:list){
            if(cartWithProduct.isChecked()==1){
                totalPrice=totalPrice.add(cartWithProduct.getTotalPrice());
            }
        }
    }
}
