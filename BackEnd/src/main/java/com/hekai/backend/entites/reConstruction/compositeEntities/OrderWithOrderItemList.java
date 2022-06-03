package com.hekai.backend.entites.reConstruction.compositeEntities;

import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyOrderItem;
import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.sourceEntites.Order;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class OrderWithOrderItemList {
    private Long orderNo;
    private BigDecimal amount;
    private int type;
    private String typeDesc;
    private int freight;
    private int status;
    private String statusDesc;
    private Timestamp paymentTime;
    private Timestamp deliveryTime;
    private Timestamp finishTime;
    private Timestamp closeTime;
    private Timestamp created;
    private List<SimplifyOrderItem> orderItems;
    private int addId;
    private String deliveryName;
    private Address address;
    private OrderWithOrderItemList(){

    }

    public OrderWithOrderItemList(@NotNull Order order, @NotNull List<SimplifyOrderItem> items,@NotNull Address address){
        this.orderNo=order.getOrderNo();
        this.amount=this.amount.add(order.getAmount());
        this.type=order.getType();
        //付款类型，1代表在线支付，2代表货到付款
        switch (order.getType()) {
            case 1 -> this.typeDesc="在线支付";
            case 2 -> this.typeDesc="货到付款";
        }
        this.freight=order.getFreight();
        this.status=order.getStatus();
        /**
         * 1.未付款
         * 2.已付款
         * 3.已发货
         * 4.交易成功
         * 5.交易关闭
         * 6.已取消
         * */
        switch (order.getStatus()){
            case 1 -> this.statusDesc="未付款";
            case 2 -> this.statusDesc="已付款";
            case 3 -> this.statusDesc="已发货";
            case 4 -> this.statusDesc="交易成功";
            case 5 -> this.statusDesc="交易关闭";
            case 6 -> this.statusDesc="已取消";
        }
        //TODO 暂时不知道写啥
        this.paymentTime=null;
        this.deliveryTime=order.getUpdated();

        //TODO 暂时也不知道写啥
        this.finishTime=null;
        this.closeTime=null;

        this.created=order.getCreated();
        this.orderItems=items;
        this.addId=address.getId();
        //这里的名字是直接从地址中取的，不知道对不对，或许应该按照order表中的uid在user中找？
        this.deliveryName=address.getName();
        this.address=address;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
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

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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

    public List<SimplifyOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<SimplifyOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    public OrderWithOrderItemList convertFromOrder(@NotNull Order order, @NotNull List<SimplifyOrderItem> items,@NotNull Address address){
//        setOrderNo(order.getOrderNo());
//        setAmount(order.getAmount());
//        setType(order.getType());
//        //付款类型，1代表在线支付，2代表货到付款
//        switch (order.getType()) {
//            case 1 -> setTypeDesc("在线支付");
//            case 2 -> setTypeDesc("货到付款");
//        }
//        setFreight(order.getFreight());
//        setStatus(order.getStatus());
//        /**
//         * 1.未付款
//         * 2.已付款
//         * 3.已发货
//         * 4.交易成功
//         * 5.交易关闭
//         * 6.已取消
//         * */
//        switch (order.getStatus()){
//            case 1 -> setStatusDesc("未付款");
//            case 2 -> setStatusDesc("已付款");
//            case 3 -> setStatusDesc("已发货");
//            case 4 -> setStatusDesc("交易成功");
//            case 5 -> setStatusDesc("交易关闭");
//            case 6 -> setStatusDesc("已取消");
//        }
//        //TODO 暂时不知道写啥
//        setPaymentTime(null);
//        setDeliveryTime(order.getUpdated());
//
//        //TODO 暂时也不知道写啥
//        setFinishTime(null);
//        setCloseTime(null);
//
//        setCreated(order.getCreated());
//        setOrderItems(items);
//        setAddId(address.getId());
//        //这里的名字是直接从地址中取的，不知道对不对，或许应该按照order表中的uid在user中找？
//        setDeliveryName(address.getName());
//        setAddress(address);
//        return this;
//    }
}
