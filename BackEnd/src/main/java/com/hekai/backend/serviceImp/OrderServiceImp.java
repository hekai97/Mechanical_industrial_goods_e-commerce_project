package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.OrderWithOrderItemList;
import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyOrderItem;
import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.sourceEntites.Order;
import com.hekai.backend.entites.sourceEntites.OrderItem;
import com.hekai.backend.repository.AddressRepository;
import com.hekai.backend.repository.OrderItemRepository;
import com.hekai.backend.repository.OrderRepository;
import com.hekai.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Result<List<OrderWithOrderItemList>> findOrderNoPages(@Nullable Long orderNo) {
        List<OrderWithOrderItemList> allOrderWithOrderItemList=new ArrayList<>();
        if(orderNo==null){
            List<Order> allOrder=orderRepository.findAll();
            for (Order order:allOrder) {
                List<OrderItem> allOrderItem=orderItemRepository.findAllByOrderId(order.getOrderNo());
                List<SimplifyOrderItem> allSimplifyOrderItem=new ArrayList<>();
                for(OrderItem orderItem:allOrderItem){
                    SimplifyOrderItem simplifyOrderItem=new SimplifyOrderItem();
                    simplifyOrderItem=simplifyOrderItem.convertFromOrderItem(order.getOrderNo(),orderItem);
                    allSimplifyOrderItem.add(simplifyOrderItem);
                }
                Address address=addressRepository.findAddressById(order.getAddrId());
                OrderWithOrderItemList orderWithOrderItemList=new OrderWithOrderItemList();
                orderWithOrderItemList=orderWithOrderItemList.convertFromOrder(order,allSimplifyOrderItem,address);
                allOrderWithOrderItemList.add(orderWithOrderItemList);
            }
        }else{
            Order order=orderRepository.findOrderByOrderNo(orderNo);
            List<OrderItem> allOrderItem=orderItemRepository.findAllByOrderId(orderNo);
            List<SimplifyOrderItem> allSimplifyOrderItem=new ArrayList<>();
            for(OrderItem orderItem:allOrderItem){
                SimplifyOrderItem simplifyOrderItem=new SimplifyOrderItem();
                simplifyOrderItem=simplifyOrderItem.convertFromOrderItem(order.getOrderNo(),orderItem);
                allSimplifyOrderItem.add(simplifyOrderItem);
            }
            Address address=addressRepository.findAddressById(order.getAddrId());
            OrderWithOrderItemList orderWithOrderItemList=new OrderWithOrderItemList();
            orderWithOrderItemList=orderWithOrderItemList.convertFromOrder(order,allSimplifyOrderItem,address);
            allOrderWithOrderItemList.add(orderWithOrderItemList);
        }
        Result<List<OrderWithOrderItemList>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(allOrderWithOrderItemList);
        return result;
    }
}
