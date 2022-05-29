package com.hekai.backend.entites.reConstruction.singleEntites;

import com.hekai.backend.entites.sourceEntites.OrderItem;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class SimplifyOrderItem {
    private Long orderNo;
    private int goodsId;
    private String goodsName;
    private String iconUrl;
    private BigDecimal curPrice;
    private int quantity;
    private BigDecimal totalPrice;
    private Timestamp created;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
    public SimplifyOrderItem convertFromOrderItem(Long orderNoParam,OrderItem orderItem){
        setOrderNo(orderNoParam);
        setGoodsId(orderItem.getGoodsId());
        setGoodsName(orderItem.getGoodsName());
        setIconUrl(orderItem.getIconUrl());
        setCurPrice(orderItem.getPrice());
        setQuantity(orderItem.getQuantity());
        setTotalPrice(orderItem.getTotalPrice());
        setCreated(orderItem.getCreated());
        return this;
    }
}
