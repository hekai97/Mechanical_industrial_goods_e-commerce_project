package com.hekai.back.controller.backstage;


import com.hekai.back.common.ResponseCode;
import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionParam;
import com.hekai.back.pojo.User;
import com.hekai.back.service.ActionParamsService;
import com.hekai.back.service.ActionUserService;
import com.hekai.back.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mgr/param")
public class ActionParamsBackController {

	@Autowired
	private ActionParamsService actionParamsService;
	@Autowired
	private ActionUserService userService;
	
	/**
	 * 新增类型
	 * @param session
	 * @param actionParam
	 * @return
	 */
	@RequestMapping("/saveparam.do")
	@ResponseBody
	public SverResponse<String> saveParam(HttpSession session, ActionParam actionParam){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:新增类型
			return actionParamsService.addParam(actionParam);
		}
				
		return SverResponse.createByErrorMessage("您无操作权限!");
		
	}
	
	/**
	 * 修改类型
	 * @param session
	 * @param actionParam
	 * @return
	 */
	@RequestMapping("/updateparam.do")
	@ResponseBody
	public SverResponse<String> updateCategory(HttpSession session,ActionParam actionParam){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:修改类型
			return actionParamsService.updateParam(actionParam);
		}
				
		return SverResponse.createByErrorMessage("您无操作权限!");
		
	}
	/**
	 * 删除商品类型
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/delparam.do")
	@ResponseBody
	public SverResponse<String> delParam(HttpSession session,Integer id){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:删除类型
			return actionParamsService.delParam(id);
		}
				
		return SverResponse.createByErrorMessage("您无操作权限!");
		
	}
	/**
	 * 查找下一级子类型（根类型）
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/findchildren.do")
	@ResponseBody
	public SverResponse<List<ActionParam>> getChildrenParam(HttpSession session,@RequestParam(value="id",defaultValue="0")Integer id){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:获得子类型
			return actionParamsService.findParamChildren(id);
		}
				
		return SverResponse.createByErrorMessage("您无操作权限!");
	}

	@RequestMapping(value = "/findptype.do",method = RequestMethod.GET)
	@ResponseBody
	public SverResponse<List<ActionParam>> findPType(HttpSession session){
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:获得子类型
			return actionParamsService.findPType();
		}

		return SverResponse.createByErrorMessage("您无操作权限!");
	}

	@RequestMapping(value = "/findpartstype.do",method = RequestMethod.GET)
	@ResponseBody
	public SverResponse<List<ActionParam>> findPartsType(HttpSession session,Integer productTypeId){
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法:获得子类型
			return actionParamsService.findPartsType(productTypeId);
		}

		return SverResponse.createByErrorMessage("您无操作权限!");
	}
	
}
