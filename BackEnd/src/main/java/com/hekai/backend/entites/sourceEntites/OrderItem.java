package com.hekai.backend.entites.sourceEntites;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Entity
@Table(name = "orderitem", schema = "mydatabase")
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "uid")
    private int uid;
    @Basic
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "goods_id")
    private int goodsId;
    @Basic
    @Column(name = "goods_name")
    private String goodsName;
    @Basic
    @Column(name = "icon_url")
    private String iconUrl;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "quantity")
    private int quantity;
    @Basic
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Basic
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "updated")
    private Timestamp updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderitem = (OrderItem) o;
        return id == orderitem.id && uid == orderitem.uid && orderId == orderitem.orderId && goodsId == orderitem.goodsId && quantity == orderitem.quantity && Objects.equals(totalPrice, orderitem.totalPrice) && Objects.equals(goodsName, orderitem.goodsName) && Objects.equals(iconUrl, orderitem.iconUrl) && Objects.equals(price, orderitem.price) && Objects.equals(created, orderitem.created) && Objects.equals(updated, orderitem.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, orderId, goodsId, goodsName, iconUrl, price, quantity, totalPrice, created, updated);
    }
}
