package com.hekai.back.controller.backstage;

import com.hekai.back.common.ResponseCode;
import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.User;
import com.hekai.back.service.ActionUserService;
import com.hekai.back.utils.ConstUtil;
import com.hekai.back.vo.ActionUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mgr/user")
public class ActionUserBackController {
	
	@Autowired
	private ActionUserService userService;
	/**
	 * 前端登陆
	 * @param session
	 * 			会话
	 * @param account
	 * 			账号
	 * @param password
	 * 			密码
	 * @return
	 */
	@RequestMapping(value = "/login.do" , method = RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> doLogin(HttpSession session,String account,String password) {
			//1.调用Service：登陆
			SverResponse<User> response=userService.doLogin(account, password);
			//2.判断能否登陆
			if(response.isSuccess()) {
				//3.能登陆判断是否是管理员，是管理员存放session，否则错误信息
				User user=response.getData();
				if(user.getRole()== ConstUtil.Role.ROLE_ADMIN) {
					session.setAttribute(ConstUtil.CUR_USER, user);
					return response;
				}
				return SverResponse.createByErrorMessage("不是管理员，无法登陆!");
			}
			return response;
	}
	/**
	 * 获得全部用户对象
	 * @param session
	 * @return
	 */
	@RequestMapping("/finduserlist.do")
	@ResponseBody
	public SverResponse<List<ActionUserVo>> getUserDetail(HttpSession session) {
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法返回所有用户信息
			return userService.findUserList();
		}
		
		return SverResponse.createByErrorMessage("您无操作权限!");
		
	}
	/**
	 * 根据id获得用户对象
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/finduser.do")
	@ResponseBody
	public SverResponse<ActionUserVo> findUser(HttpSession session, Integer id){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法根据用户id获得用户信息
			return userService.findUserById(id);
			
		}

		return SverResponse.createByErrorMessage("您无操作权限!");
		
	}
	/**
	 * 更新用户信息
	 * @param session
	 * @param actionUserVo
	 * @return
	 */
	@RequestMapping("/updateuser.do")
	@ResponseBody
	public SverResponse<User> updateUser(HttpSession session,ActionUserVo actionUserVo){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法更新用户
			return userService.updateUserInfo(actionUserVo);
		}

		return SverResponse.createByErrorMessage("您无操作权限!");
		
	}
	/**
	 * 删除用户
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteusers.do")
	@ResponseBody
	public SverResponse<String> delUsers(HttpSession session,Integer id){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法删除用户
			return userService.delUser(id);
		}

		return SverResponse.createByErrorMessage("您无操作权限!");
	}
}
