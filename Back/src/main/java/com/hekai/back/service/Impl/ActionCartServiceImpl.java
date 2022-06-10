package com.hekai.back.service.Impl;

import com.google.common.collect.Lists;
import com.hekai.back.common.SverResponse;
import com.hekai.back.dao.ActionCartDao;
import com.hekai.back.dao.ActionProductDao;
import com.hekai.back.pojo.ActionCart;
import com.hekai.back.pojo.ActionProduct;
import com.hekai.back.service.ActionCartService;
import com.hekai.back.utils.CalcUtil;
import com.hekai.back.vo.ActionCartVo;
import com.hekai.back.vo.ActionCartsListVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Service
public class ActionCartServiceImpl implements ActionCartService {
	@Autowired
	private ActionCartDao actionCartDao;
	@Autowired
	private ActionProductDao actionProductDao;

	@Override
	public SverResponse<String> saveOrUpdate(Integer userId, Integer productId, Integer count) {
		// TODO 自动生成的方法存根
		//1.验证参数是否正确
		if(userId == null || productId == null || count == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.查看用户的购物车中是否存在商品
		ActionCart actionCart = actionCartDao.findCartByUserAndProductId(userId,productId);
		if (actionCart == null) {
			//3.不存在,则新增
			ActionCart cart = new ActionCart();
			cart.setUser_id(userId);
			cart.setProduct_id(productId);
			cart.setQuantity(count);
			Date curDate = new Date();
			cart.setCreated(curDate);
			cart.setUpdated(curDate);
			int rs = actionCartDao.insertCart(cart);
			if (rs == 0) {
				return SverResponse.createByErrorMessage("商品新增失败!");
			}
		}else {
			//4.存在,则数量增加
			int cartCount = actionCart.getQuantity() + count;
			actionCart.setQuantity(cartCount);
			actionCart.setUpdated(new Date());
			int rs = actionCartDao.updateCartById(actionCart);
			if (rs == 0) {
				return SverResponse.createByErrorMessage("商品数量增加失败!");
			}
		}
		return SverResponse.createRespBySuccessMessage("商品已成功加入到购物车!");
	}

	@Override
	public SverResponse<ActionCartVo> findAllCarts(Integer userId) {
		// TODO 自动生成的方法存根
		//1.验证参数是否正确
		if(userId == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.查找用户购物车中的商品
		List<ActionCart> list = actionCartDao.findCartByUser(userId);
		//3.封装actionCartVo对象
		ActionCartVo actionCartVo = createCartVo(list);
		return SverResponse.createRespBySuccess(actionCartVo);
	}
	/**
	 * 封装购物车vo对象
	 * @param carts
	 * @return
	 */
	private ActionCartVo createCartVo(List<ActionCart> carts) {
		// TODO 自动生成的方法存根
		ActionCartVo actionCartVo = new ActionCartVo();
		List<ActionCartsListVo> list = Lists.newArrayList();
		//购物车商品总价格
		BigDecimal cartTotalPrice = new BigDecimal("0");
		if (CollectionUtils.isNotEmpty(carts)) {
			for (ActionCart cart : carts) {
				//转换对象
				ActionCartsListVo listVo = new ActionCartsListVo();
				listVo.setId(cart.getId());
				listVo.setUserId(cart.getUser_id());
				listVo.setProductId(cart.getProduct_id());
				listVo.setChecked(cart.getChecked());
				//封装商品信息
				ActionProduct product = actionProductDao.findProductById(listVo.getProductId());
				if (product != null) {
					listVo.setName(product.getName());
					listVo.setStatus(product.getStatus());
					listVo.setPrice(product.getPrice());
					listVo.setStock(product.getStock());
					listVo.setIconUrl(product.getIconUrl());
					int buyCount = 0;
					if (product.getStock() >= cart.getQuantity()) {
						buyCount = cart.getQuantity();
					}else {
						buyCount = product.getStock();
						//更新购物车数量
						ActionCart updateCart = new ActionCart();
						updateCart.setId(cart.getId());
						updateCart.setQuantity(buyCount);
						updateCart.setUpdated(new Date());
						//更新选中状态
						updateCart.setChecked(cart.getChecked());
						actionCartDao.updateCartById(updateCart);
					}
					listVo.setQuantity(buyCount);
					//计算购物车中某商品的总价格
					BigDecimal totalPrice = CalcUtil.mul(listVo.getPrice().doubleValue(), listVo.getQuantity().doubleValue());
					listVo.setTotalPrice(totalPrice);
					if (cart.getChecked() == 1) {
						//在总价格中加入此商品总价格
						cartTotalPrice = CalcUtil.add(cartTotalPrice.doubleValue(), listVo.getTotalPrice().doubleValue());
					}
				}
				list.add(listVo);
			}
		}
		actionCartVo.setLists(list);
		actionCartVo.setTotalPrice(cartTotalPrice);
		return actionCartVo;
	}

	@Override
	public SverResponse<String> clearCart(Integer userId) {
		// TODO 自动生成的方法存根
		//1.验证参数是否正确
		if(userId == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.清空购物车,判断正确
		int rs = actionCartDao.deleteCartByUserId(userId);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("成功清空购物车!");
		}
		return SverResponse.createByErrorMessage("清空购物车失败!");
	}

	@Override
	public SverResponse<ActionCartVo> updateCart(Integer userId, Integer productId, Integer count, Integer checked) {
		// TODO 自动生成的方法存根
		//1.验证参数是否正确
		if(userId == null || productId == null || count == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.更新购物车信息
		ActionCart actionCart = new ActionCart();
		actionCart.setUser_id(userId);
		actionCart.setProduct_id(productId);
		actionCart.setQuantity(count);
		actionCart.setChecked(checked);
		actionCart.setUpdated(new Date());
		actionCartDao.updateCartByUserIdAndProductId(actionCart);
		//3.返回所有购物车信息
		return findAllCarts(userId);
	}

	@Override
	public SverResponse<ActionCartVo> deleteCart(Integer userId, Integer productId) {
		// TODO 自动生成的方法存根
		//1.验证参数是否正确
		if(userId == null || productId == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.删除购物车中的某条商品信息
		int rs = actionCartDao.deleteCartByUserIdAndProductId(userId,productId);
		if (rs == 0) {
			return SverResponse.createByErrorMessage("删除商品失败!");
		}
		//3.返回所有购物车信息
		return findAllCarts(userId);
	}

	@Override
	public SverResponse<Integer> getCartsCount(Integer userId) {
		// TODO 自动生成的方法存根
		//1.验证参数是否正确
		if(userId == null) {
			return SverResponse.createByErrorMessage("参数错误!");
		}
		//2.返回购物车中商品数量
		int count = actionCartDao.getCartCountByUserId(userId);
		return SverResponse.createRespBySuccess(Integer.valueOf(count));
	}
}
