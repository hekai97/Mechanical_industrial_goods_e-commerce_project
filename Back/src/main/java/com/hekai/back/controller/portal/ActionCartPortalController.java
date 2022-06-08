package com.hekai.back.controller.portal;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.User;
import com.hekai.back.service.ActionCartService;
import com.hekai.back.utils.ConstUtil;
import com.hekai.back.vo.ActionCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/cart")
public class ActionCartPortalController {
	@Autowired
	private ActionCartService actionCartService;
	/**
	 * 加入购物车
	 * @param session
	 * @param productId
	 * @param count
	 * @return
	 */
	@RequestMapping(value="/savecart.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> addProductCart(HttpSession session,Integer productId,Integer count){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.saveOrUpdate(user.getId(),productId,count);
	}
	/**
	 * 查看购物车
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/findallcarts.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<ActionCartVo> findAllCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.findAllCarts(user.getId());
	}
	/**
	 * 清空购物车
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/clearcarts.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<String> clearCarts(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.clearCart(user.getId());
	}
	/**
	 * 更新购物车
	 * @param session
	 * @param productId
	 * @param count
	 * @param checked
	 * @return
	 */
	@RequestMapping(value="/updatecarts.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<ActionCartVo> updateCarts(HttpSession session,Integer productId,Integer count,Integer checked){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.updateCart(user.getId(),productId,count,checked);
	}
	/**
	 * 删除购物车中的某条商品
	 * @param session
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="/delcarts.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<ActionCartVo> deleteCarts(HttpSession session,Integer productId){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.deleteCart(user.getId(),productId);
	}
	/**
	 * 购物车商品数量
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getcartcount.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<Integer> getCartsCount(HttpSession session){
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionCartService.getCartsCount(user.getId());
	}
}
