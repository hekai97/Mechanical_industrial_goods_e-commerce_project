package com.hekai.back.service.Impl;

import com.google.common.collect.Lists;
import com.hekai.back.common.SverResponse;
import com.hekai.back.dao.*;
import com.hekai.back.pojo.*;
import com.hekai.back.service.ActionOrderService;
import com.hekai.back.utils.CalcUtil;
import com.hekai.back.utils.ConstUtil;
import com.hekai.back.utils.DateUtils;
import com.hekai.back.utils.PageBean;
import com.hekai.back.vo.ActionAddressVo;
import com.hekai.back.vo.ActionOrderItemVo;
import com.hekai.back.vo.ActionOrderVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ActionOrderServiceImpl implements ActionOrderService {
	
	@Autowired
	private ActionOrderDao actionOrderDao;
	@Autowired
	private ActionAddressDao actionAddressDao;
	@Autowired
	private ActionOrderItemDao actionOrderItemDao;
	@Autowired
	private ActionCartDao actionCartDao;
	@Autowired
	private ActionProductDao actionProductDao;
	
	@Override
	public SverResponse<PageBean<ActionOrderVo>> findOrders(Integer userId, Integer status, int pageNum, int pageSize) {
		// TODO 自动生成的方法存根
		//1.判断参数是否合法
		if (userId == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.查找符合条件的总记录数
		int totalRecord = actionOrderDao.getTotalRecord(userId,status);
		//3.创建分页封装对象
		PageBean<ActionOrderVo> pageBean = new PageBean<ActionOrderVo>(pageNum,pageSize, totalRecord);
		//4.调用dao层类中方法读取数据
		List<ActionOrder> orders = actionOrderDao.findOrders(userId,status,pageBean.getStartIndex(),pageBean.getPageSize());
		//5.封装vo
		List<ActionOrderVo> voList = Lists.newArrayList();
		for (ActionOrder order : orders) {
			voList.add(createOrderVo(order,false));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
	/**
	 * 封装vo
	 * @param order
	 * @param hasAddress
	 * @return
	 */
	private ActionOrderVo createOrderVo(ActionOrder order, boolean hasAddress) {
		// TODO 自动生成的方法存根
		ActionOrderVo orderVo = new ActionOrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,hasAddress);
		//设置订单项
		List<ActionOrderItem> orderItems = actionOrderItemDao.getItemsByOrderNo(order.getOrder_no());
		setOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	/**
	 * 封装订单vo
	 * @param order
	 * @param orderItems
	 * @return
	 */
	private ActionOrderVo createOrderVo1(ActionOrder order, List<ActionOrderItem> orderItems) {
		// TODO 自动生成的方法存根
		ActionOrderVo orderVo = new ActionOrderVo();
		setNormalProperty(order,orderVo);
		setAddressProperty(order,orderVo,true);
		//设置订单项
		setOrderItemProperty(orderItems,orderVo);
		return orderVo;
	}
	/**
	 * 封装订单项属性
	 * @param orderItems
	 * @param orderVo
	 */
	private void setOrderItemProperty(List<ActionOrderItem> orderItems, ActionOrderVo orderVo) {
		// TODO 自动生成的方法存根
		List<ActionOrderItemVo> items = Lists.newArrayList();
		for (ActionOrderItem orderItem : orderItems) {
			items.add(createOrderItemVo(orderItem));
		}
		orderVo.setOrderItems(items);
	}
	/**
	 * 封装订单项vo
	 * @param orderItem
	 * @return
	 */
	private ActionOrderItemVo createOrderItemVo(ActionOrderItem orderItem) {
		// TODO 自动生成的方法存根
		ActionOrderItemVo itemVo = new ActionOrderItemVo();
		itemVo.setOrderNo(orderItem.getOrder_no());
		itemVo.setGoodsId(orderItem.getGoods_id());
		itemVo.setGoodsName(orderItem.getGoods_name());
		itemVo.setCurPrice(orderItem.getPrice());
		itemVo.setIconUrl(orderItem.getIcon_url());
		itemVo.setQuantity(orderItem.getQuantity());
		itemVo.setTotalPrice(orderItem.getTotal_price());
		itemVo.setCreated(DateUtils.date2Str(orderItem.getCreated()));
		return itemVo;
	}
	/**
	 * 封装地址属性
	 * @param order
	 * @param orderVo
	 * @param hasAddress
	 */
	private void setAddressProperty(ActionOrder order, ActionOrderVo orderVo, boolean hasAddress) {
		// TODO 自动生成的方法存根
		ActionAddress address = actionAddressDao.findAddrsById(order.getAddr_id());
		if (address != null) {
			orderVo.setDeliveryName(address.getName());
			if (hasAddress) {
				orderVo.setAddress(createAddressVo(address));
			}else {
				orderVo.setAddress(null);
			}
		}
	}
	/**
	 * 封装地址vo
	 * @param address
	 * @return
	 */
	private ActionAddressVo createAddressVo(ActionAddress address) {
		// TODO 自动生成的方法存根
		ActionAddressVo addressVo = new ActionAddressVo();
		addressVo.setName(address.getName());
		addressVo.setPhone(address.getPhone());
		addressVo.setMobile(address.getMobile());
		addressVo.setProvince(address.getProvince());
		addressVo.setCity(address.getCity());
		addressVo.setDistrict(address.getDistrict());
		addressVo.setZip(address.getZip());
		addressVo.setAddr(address.getAddr());
		return addressVo;
	}
	/**
	 * 封装订单的vo普通属性
	 * @param order
	 * @param orderVo
	 */
	private void setNormalProperty(ActionOrder order, ActionOrderVo orderVo) {
		// TODO 自动生成的方法存根
		orderVo.setOrderNo(order.getOrder_no());
		orderVo.setAmount(order.getAmount());
		orderVo.setType(order.getType());
		orderVo.setTypeDesc(ConstUtil.PaymentType.getTypeDesc(order.getType()));
		orderVo.setFreight(order.getFreight());
		orderVo.setStatus(order.getStatus());
		orderVo.setStatusDesc(ConstUtil.OrderStatus.getStatusDesc(order.getStatus()));
		orderVo.setAddrId(order.getAddr_id());
		//时间
		orderVo.setPaymentTime(DateUtils.date2Str(order.getPayment_time()));
		orderVo.setDeliveryTime(DateUtils.date2Str(order.getDelivery_time()));
		orderVo.setFinishTime(DateUtils.date2Str(order.getFinish_time()));
		orderVo.setCloseTime(DateUtils.date2Str(order.getClose_time()));
		orderVo.setCreated(DateUtils.date2Str(order.getCreated()));
	}
	@Override
	public SverResponse<String> cancelOrConfirmOrder(Integer userId, Long orderNo) {
		// TODO 自动生成的方法存根
		//1.查询订单
		ActionOrder order = actionOrderDao.findOrderByUserAndOrderNo(userId,orderNo);
		//2.判断订单是否存在
		if (order == null) {
			return SverResponse.createByErrorMessage("该用户订单不存在,或已删除!");
		}
		//3.订单是否已付款
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_PAID) {
			return SverResponse.createByErrorMessage("该订单已经付款,无法取消");
		}
		//4.根据状态修改订单信息
		ActionOrder updateOrder = new ActionOrder();
		updateOrder.setId(order.getId());
		updateOrder.setUpdated(new Date());
		//5.为取消订单操作
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_NO_PAY) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_CANCELED);
			int rs = actionOrderDao.updateOrder(updateOrder);
			if (rs > 0) {
				return SverResponse.createRespBySuccessMessage("取消订单成功!");
			}
		}
		//6.为确认收货操作
		if (order.getStatus() == ConstUtil.OrderStatus.ORDER_SHIPPED) {
			updateOrder.setStatus(ConstUtil.OrderStatus.ORDER_SUCCESS);
			int rs = actionOrderDao.updateOrder(updateOrder);
			if (rs > 0) {
				return SverResponse.createRespBySuccessMessage("确认收货成功!");
			}
		}
		return SverResponse.createByErrorMessage("订单操作失败!");
	}
	@Override
	public SverResponse<ActionOrderVo> findOrderDetail(Integer userId, Long orderNo) {
		// TODO 自动生成的方法存根
		//1.判断参数是否合法
		if (userId == null || orderNo == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.查找订单,封装vo
		ActionOrder order = actionOrderDao.findOrderByUserAndOrderNo(userId, orderNo);
		if (order == null) {
			return SverResponse.createByErrorMessage("该用户订单不存在,或已删除!");
		}
		ActionOrderVo orderVo = createOrderVo(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}
	@Override
	public SverResponse<ActionOrderVo> generateOrder(Integer userId, Integer addrId) {
		// TODO 自动生成的方法存根
		//1.判断参数是否合法
		if (userId == null || addrId == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.提取购物车中信息
		List<ActionCart> carts = actionCartDao.findCartByUser(userId);
		//3.计算购物车中每件商品总价格并生成订单项
		SverResponse resp = this.cart2OrderItem(userId,carts);
		if (!resp.isSuccess()) {
			return resp;
		}
		//4.取出订单项中的价格计算订单总价格
		List<ActionOrderItem> orderItems = (List<ActionOrderItem>) resp.getData();
		BigDecimal totalPrice = this.calcOrderTotalPrice(orderItems);
		//5.生成订单,插入数据
		ActionOrder order = saveOrder(userId,addrId,totalPrice);
		if (order == null) {
			return SverResponse.createByErrorMessage("订单产生错误,请检查后重新提交!");
		}
		if (CollectionUtils.isEmpty(orderItems)) {
			return SverResponse.createByErrorMessage("订单项为空,请选择要购买的商品!");
		}
		//6.批量插入订单项
		for (ActionOrderItem orderItem : orderItems) {
			//为订单项设置订单主键
			orderItem.setOrder_no(order.getOrder_no());
		}
		actionOrderItemDao.batchInsert(orderItems);
		//7.减少商品表中的库存
		for (ActionOrderItem orderItem : orderItems) {
			ActionProduct product = actionProductDao.findProductById(orderItem.getGoods_id());
			//减少库存
			product.setStock(product.getStock() - orderItem.getQuantity());
			product.setUpdated(new Date());
			//更新库存
			actionProductDao.updateProduct(product);
		}
		//8.清空购物车
		for (ActionCart cart:carts) {
			if (cart.getChecked() == 1) {
				actionCartDao.deleteCartByUserIdAndProductId(userId, cart.getProduct_id());
			}
		}
		//9.封装返回前端
		ActionOrderVo orderVo = createOrderVo1(order,orderItems);
		return SverResponse.createRespBySuccess(orderVo);
	}
	/**
	 * 保存订单
	 * @param userId
	 * @param addrId
	 * @param totalPrice
	 * @return
	 */
	private ActionOrder saveOrder(Integer userId, Integer addrId, BigDecimal totalPrice) {
		// TODO 自动生成的方法存根
		ActionOrder order = new ActionOrder();
		//生成订单号
		long currentTime = System.currentTimeMillis();
		long orderNo = currentTime + new Random().nextInt(100);
		order.setOrder_no(orderNo);
		order.setStatus(ConstUtil.OrderStatus.ORDER_NO_PAY);	//默认未付款
		order.setType(ConstUtil.PaymentType.PAY_ON_LINE);		//在线支付
		order.setFreight(0);
		order.setAmount(totalPrice);
		order.setAddr_id(addrId);
		order.setUid(userId);
		order.setUpdated(new Date());
		order.setCreated(new Date());
		//插入订单
		int rs = actionOrderDao.insertOrder(order);
		if (rs > 0) {
			//插入成功
			return order;
		}
		return null;
	}
	/**
	 * 计算订单总价格
	 * @param orderItems
	 * @return
	 */
	private BigDecimal calcOrderTotalPrice(List<ActionOrderItem> orderItems) {
		// TODO 自动生成的方法存根
		BigDecimal totalPrice = new BigDecimal("0");
		for (ActionOrderItem item : orderItems) {
			totalPrice = CalcUtil.add(totalPrice.doubleValue(), item.getTotal_price().doubleValue());
		}
		return totalPrice;
	}
	/**
	 * 将购物车中商品封装为订单项
	 * @param userId
	 * @param carts
	 * @return
	 */
	private SverResponse cart2OrderItem(Integer userId, List<ActionCart> carts) {
		// TODO 自动生成的方法存根
		List<ActionOrderItem> items = Lists.newArrayList();
		//判断购物车是否为空
		if (CollectionUtils.isEmpty(carts)) {
			return SverResponse.createByErrorMessage("购物车为空,请选择要购买的商品!");
		}
		for (ActionCart cart : carts) {
			if (cart.getChecked() == 1) {
			//查看购物车中商品状态
			ActionProduct product = actionProductDao.findProductById(cart.getProduct_id());
			//查看商品状态
			if (ConstUtil.ProductStatus.STATUS_ON_SALE != product.getStatus()) {
				//如果商品不是上架在售,则返回提示信息
				return SverResponse.createByErrorMessage("商品"+product.getName()+"已经下架,不能在线购买!");
			}
			//查看库存
			if (cart.getQuantity() > product.getStock()) {
				//如果商品库存不足,则返回提示信息
				return SverResponse.createByErrorMessage("商品"+product.getName()+"库存不足!");
			}
			//封装订单项
			ActionOrderItem orderItem = new ActionOrderItem();
			orderItem.setUid(userId);
			orderItem.setGoods_name(product.getName());
			orderItem.setGoods_id(product.getId());
			orderItem.setIcon_url(product.getIconUrl());
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(cart.getQuantity());
			orderItem.setTotal_price(CalcUtil.mul(product.getPrice().doubleValue(), cart.getQuantity().doubleValue()));
			orderItem.setCreated(new Date());
			orderItem.setUpdated(new Date());
			items.add(orderItem);
			}
		}
		return SverResponse.createRespBySuccess(items);
	}
	@Override
	public SverResponse<List<ActionOrderVo>> findOrderForNoPages(Long orderNo) {
		// TODO Auto-generated method stub
		//1.调用dao层的方法
		List<ActionOrder> orders=actionOrderDao.searchOrders(orderNo);
		//2.转换成vo对象
		List<ActionOrderVo> vos=Lists.newArrayList();
		for(ActionOrder temp:orders) {
			//转换成vo
			vos.add(this.createOrderVo(temp, true));
		}
		return SverResponse.createRespBySuccess(vos);
	}
	@Override
	public SverResponse<ActionOrderVo> mgrDetail(Long orderNo) {
		// TODO 自动生成的方法存根
		//1.订单号的判断
		if (orderNo == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.调用dao层的方法根据订单号获得订单对象ActionOrder
		ActionOrder order = actionOrderDao.findOrderDetailByOrderNo(orderNo);
		if (order == null) {
			return SverResponse.createByErrorMessage("该用户订单不存在,或已删除!");
		}
		//3.将order对象转换成orderVo
		ActionOrderVo orderVo = this.createOrderVo(order, true);
		return SverResponse.createRespBySuccess(orderVo);
	}
}
