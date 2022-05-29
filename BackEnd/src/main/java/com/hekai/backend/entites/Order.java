package com.hekai.backend.entites;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Entity
@Table(name = "order", schema = "mydatabase")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "order_no")
    private long orderNo;
    @Basic
    @Column(name = "uid")
    private int uid;
    @Basic
    @Column(name = "addr_id")
    private int addrId;
    @Basic
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic
    @Column(name = "type")
    private int type;
    @Basic
    @Column(name = "freight")
    private int freight;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "payment_time")
    private Timestamp paymentTime;
    @Basic
    @Column(name = "delivery_time")
    private Timestamp deliveryTime;
    @Basic
    @Column(name = "finish_time")
    private Timestamp finishTime;
    @Basic
    @Column(name = "close_time")
    private Timestamp closeTime;
    @Basic
    @Column(name = "created")
    private Timestamp created;
    @Basic
    @Column(name = "updated")
    private Integer updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAddrId() {
        return addrId;
    }

    public void setAddrId(int addrId) {
        this.addrId = addrId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && orderNo == order.orderNo && uid == order.uid && addrId == order.addrId && type == order.type && freight == order.freight && status == order.status && Objects.equals(amount, order.amount) && Objects.equals(paymentTime, order.paymentTime) && Objects.equals(deliveryTime, order.deliveryTime) && Objects.equals(finishTime, order.finishTime) && Objects.equals(closeTime, order.closeTime) && Objects.equals(created, order.created) && Objects.equals(updated, order.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNo, uid, addrId, amount, type, freight, status, paymentTime, deliveryTime, finishTime, closeTime, created, updated);
    }
}
