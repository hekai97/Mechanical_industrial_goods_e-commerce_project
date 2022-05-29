package com.hekai.backend.entites.reconstruction;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class CartWithProductAndTotalPrice {
    private List<CartWithProduct> list;
    private BigDecimal totalPrice;

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
}
