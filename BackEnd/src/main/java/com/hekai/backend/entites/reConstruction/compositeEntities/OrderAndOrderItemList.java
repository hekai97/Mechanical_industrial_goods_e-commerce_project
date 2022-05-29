package com.hekai.backend.entites.reConstruction.compositeEntities;

import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.sourceEntites.Order;
import com.hekai.backend.entites.sourceEntites.OrderItem;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
public class OrderAndOrderItemList {
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private Order order;
    private List<OrderItem> orderItems;
    private Address address;
}
