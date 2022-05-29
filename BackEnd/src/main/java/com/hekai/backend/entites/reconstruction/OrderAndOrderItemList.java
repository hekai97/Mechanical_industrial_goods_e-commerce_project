package com.hekai.backend.entites.reconstruction;

import com.hekai.backend.entites.Address;
import com.hekai.backend.entites.Order;
import com.hekai.backend.entites.OrderItem;

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
