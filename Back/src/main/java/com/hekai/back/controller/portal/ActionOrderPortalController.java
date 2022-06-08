package com.hekai.back.controller.portal;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.User;
import com.hekai.back.service.ActionOrderService;
import com.hekai.back.utils.ConstUtil;
import com.hekai.back.utils.PageBean;
import com.hekai.back.vo.ActionOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class ActionOrderPortalController {
	@Autowired
	private ActionOrderService actionOrderService;

	/**
	 * 获取订单列表
	 * @param session
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getlist.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<PageBean<ActionOrderVo>> getList(HttpSession session,Integer status,
														 @RequestParam(value="pageNum",defaultValue="1") int pageNum,
														 @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		//判断用户是否登陆
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.findOrders(user.getId(),status,pageNum,pageSize);
	}
	/**
	 * 取消订单
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value="/cancelorder.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> cancelOrder(HttpSession session,Long orderNo){
		//判断用户是否登陆
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.cancelOrConfirmOrder(user.getId(),orderNo);
	}
	/**
	 * 确认收货
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value="/confirmreceipt.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> confirmOrder(HttpSession session,Long orderNo){
		//判断用户是否登陆
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.cancelOrConfirmOrder(user.getId(),orderNo);
	}
	/**
	 * 获取订单详细信息
	 * @param session
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(value="/getdetail.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<ActionOrderVo> getDetail(HttpSession session,Long orderNo){
		//判断用户是否登陆
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.findOrderDetail(user.getId(),orderNo);
	}
	/**
	 * 创建订单
	 * @param session
	 * @param addrId
	 * @return
	 */
	@RequestMapping(value="/createorder.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<ActionOrderVo> createOrder(HttpSession session,Integer addrId){
		//判断用户是否登陆
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后再操作!");
		}
		return actionOrderService.generateOrder(user.getId(),addrId);
	}
}
