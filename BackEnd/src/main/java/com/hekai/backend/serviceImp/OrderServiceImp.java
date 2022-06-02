package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.OrderAndOrderItemList;
import com.hekai.backend.entites.reConstruction.compositeEntities.PageBean;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.compositeEntities.OrderWithOrderItemList;
import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyOrderItem;
import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.sourceEntites.Order;
import com.hekai.backend.entites.sourceEntites.OrderItem;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.repository.AddressRepository;
import com.hekai.backend.repository.OrderItemRepository;
import com.hekai.backend.repository.OrderRepository;
import com.hekai.backend.service.OrderService;
import com.hekai.backend.utils.ConstUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                OrderWithOrderItemList orderWithOrderItemList=getOrderWithOrderItemListFromOrder(order);
                allOrderWithOrderItemList.add(orderWithOrderItemList);
            }
        }else{
            Order order=orderRepository.findOrderByOrderNo(orderNo);
            OrderWithOrderItemList orderWithOrderItemList=getOrderWithOrderItemListFromOrder(order);
            allOrderWithOrderItemList.add(orderWithOrderItemList);
        }
        Result<List<OrderWithOrderItemList>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(allOrderWithOrderItemList);
        return result;
    }

    @Override
    public Result<PageBean<OrderWithOrderItemList>> searchOrderAndItem(Long orderNo, int pageNum, int pageSize) {
        Order order= orderRepository.findOrderByOrderNo(orderNo);
        List<OrderItem> items;
        Pageable pageable=PageRequest.of(pageNum,pageSize);
        Page<OrderItem> pageItems=orderItemRepository.findByOrderId(orderNo,pageable);
        int totalPageNumber=pageItems.getTotalPages();
        items=pageItems.getContent();
        List<SimplifyOrderItem> simplifyOrderItems=new ArrayList<>();
        for(OrderItem item:items){
            SimplifyOrderItem simplifyOrderItem=new SimplifyOrderItem(orderNo,item);
            simplifyOrderItems.add(simplifyOrderItem);
        }
        Address address=addressRepository.findAddressById(order.getAddrId());
        OrderWithOrderItemList orderWithOrderItemList=new OrderWithOrderItemList(order,simplifyOrderItems,address);
        PageBean<OrderWithOrderItemList> pageBean=new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPrePage(pageNum-1);
        pageBean.setNextPage(pageNum+1);
        if(pageItems.hasPrevious()){
            pageBean.setPrePage(pageNum-1);
        }else{
            pageBean.setPrePage(pageNum);
        }

        if(pageItems.hasNext()){
            pageBean.setNextPage(pageNum+1);
        }else{
            pageBean.setNextPage(pageNum);
        }
        pageBean.setPageSize(pageSize);
        pageBean.setData(orderWithOrderItemList);
        pageBean.setTotalPage(totalPageNumber);
        pageBean.setStartIndex(pageSize*(pageNum-1));
        pageBean.setTotalRecord((int)pageItems.getTotalElements());
        Result<PageBean<OrderWithOrderItemList>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(pageBean);
        return result;
    }

    @Override
    public Result<PageBean<List<OrderWithOrderItemList>>> findOrders(int pageNum, int pageSize) {
        //这块应该是分页订单，上面是分页一个订单的订单项
        Pageable pageable=PageRequest.of(pageNum,pageSize);
        List<OrderWithOrderItemList> orderWithOrderItemLists=new ArrayList<>();
        Page<Order> pageList=orderRepository.findAll(pageable);
        int totalPages=pageList.getTotalPages();
        List<Order> orderList=pageList.getContent();
        for (Order order : orderList) {
            OrderWithOrderItemList orderWithOrderItemList=getOrderWithOrderItemListFromOrder(order);
            orderWithOrderItemLists.add(orderWithOrderItemList);
        }
        PageBean<List<OrderWithOrderItemList>> pageBean=new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setData(orderWithOrderItemLists);
        pageBean.setTotalPage(totalPages);
        pageBean.setTotalRecord((int)pageList.getTotalElements());
        if(pageList.hasPrevious()){
            pageBean.setPrePage(pageNum-1);
        }else{
            pageBean.setPrePage(pageNum);
        }

        if(pageList.hasNext()){
            pageBean.setNextPage(pageNum+1);
        }else{
            pageBean.setNextPage(pageNum);
        }
        pageBean.setStartIndex(pageSize*(pageNum-1));
        Result<PageBean<List<OrderWithOrderItemList>>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(pageBean);
        return result;
    }

    @Override
    public Result<OrderWithOrderItemList> getDetail(Long orderNo) {
        Order order= orderRepository.findOrderByOrderNo(orderNo);
        OrderWithOrderItemList orderWithOrderItemList=getOrderWithOrderItemListFromOrder(order);
        return Result.createRespBySuccess(orderWithOrderItemList);
    }

    @Override
    public Result<PageBean<List<OrderWithOrderItemList>>> getOrderLists(User user, Integer status, int pageNum, int pageSize) {
        Pageable pageable=PageRequest.of(pageNum,pageSize);
        Page<Order> orderPage=orderRepository.findByUidAndStatus(user.getId(),status,pageable);
        List<Order> orderList=orderPage.getContent();
        List<OrderWithOrderItemList> orderWithOrderItemLists=new ArrayList<>();
        for(Order order:orderList){
            OrderWithOrderItemList orderWithOrderItemList=getOrderWithOrderItemListFromOrder(order);
            orderWithOrderItemLists.add(orderWithOrderItemList);
        }
        PageBean<List<OrderWithOrderItemList>> pageBean=new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setData(orderWithOrderItemLists);
        pageBean.setTotalPage(orderPage.getTotalPages());
        pageBean.setTotalRecord((int)orderPage.getTotalElements());
        if(orderPage.hasPrevious()){
            pageBean.setPrePage(pageNum-1);
        }else{
            pageBean.setPrePage(pageNum);
        }

        if(orderPage.hasNext()){
            pageBean.setNextPage(pageNum+1);
        }else{
            pageBean.setNextPage(pageNum);
        }
        pageBean.setStartIndex(pageSize*(pageNum-1));
        return Result.createRespBySuccess(pageBean);
    }

    @Override
    public Result<String> cancelOrder(User user, Long orderNo) {
        Order order=orderRepository.findOrderByOrderNo(orderNo);
        if(order==null){
            return Result.createByErrorMessage("该订单不存在");
        }
        if(order.getStatus()== ConstUtil.OrderStatus.ORDER_PAID||order.getStatus()==ConstUtil.OrderStatus.ORDER_SUCCESS){
            return Result.createByErrorMessage("该订单已付款，无法取消");
        }
        if(order.getStatus()==ConstUtil.OrderStatus.ORDER_NO_PAY){
            order.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
            orderRepository.save(order);
            return Result.createRespBySuccess("该订单已取消");
        }
        return Result.createByErrorMessage("失败");
    }

    @Override
    public Result<String> confirmReceipt(User user, Long orderNo) {
        Order order=orderRepository.findOrderByOrderNo(orderNo);
        if(order==null){
            return Result.createByErrorMessage("该订单不存在");
        }
        if(order.getStatus()==ConstUtil.OrderStatus.ORDER_SHIPPED){
            order.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
            orderRepository.save(order);
            return Result.createRespBySuccessMessage("订单确认收货成功");
        }
        return Result.createByErrorMessage("失败");
    }

    @Override
    public Result<OrderAndOrderItemList> creatOrder(User user, Integer addrId) {
        if(addrId==null){
            return Result.createByErrorMessage("参数错误");
        }
        //TODO  继续写创建订单
        return null;
    }


    //从订单转换成订单和订单项的集合
    private OrderWithOrderItemList getOrderWithOrderItemListFromOrder(Order order){
        List<OrderItem> orderItems=orderItemRepository.findAllByOrderId(order.getOrderNo());
        List<SimplifyOrderItem> simplifyOrderItems=new ArrayList<>();
        for(OrderItem t:orderItems){
            SimplifyOrderItem simplifyOrderItem=new SimplifyOrderItem(order.getOrderNo(),t);
            simplifyOrderItems.add(simplifyOrderItem);
        }
        Address address=addressRepository.findAddressById(order.getAddrId());
        return new OrderWithOrderItemList(order,simplifyOrderItems,address);
    }
}
