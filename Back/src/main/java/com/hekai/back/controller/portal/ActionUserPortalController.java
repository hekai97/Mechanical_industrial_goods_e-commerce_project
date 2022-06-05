package com.hekai.back.controller.portal;

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

@Controller
@RequestMapping("/user")
public class ActionUserPortalController {

	@Autowired
	private ActionUserService userService;
	/**
	 * 用户登录
	 * @param session
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/do_login.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> doLogin(HttpSession session, String account, String password){
		SverResponse<User> response = userService.doLogin(account, password);
		if(response.isSuccess()) {
			//登录成功,将用户信息存入session
			session.setAttribute(ConstUtil.CUR_USER, response.getData());
		}
		return response;
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/do_register.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> registerUser(User user){
		return userService.doRegister(user);
	}
	/**
	 * 验证用户并且获得用户对象
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getuserbyaccount.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> getUserByAccount(String account){
		return userService.findUserByAccount(account);
	}
	/**
	 * 得到用户信息
	 */
	@RequestMapping(value="/getuserinfo.do",method=RequestMethod.GET)
	@ResponseBody
	public SverResponse<User> getUserInfo(HttpSession session){
		User curUser = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(curUser == null) {
			return SverResponse.createByErrorMessage("用户尚未登陆!");
		}
		return userService.findUserByAccount(curUser.getAccount());
	}
	/**
	 * 得到用户密码问题
	 */
	@RequestMapping(value="/getuserquestion.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> getUserQuestion(String account){
		return userService.getUserQusetion(account);
	}
	/**
	 * 验证用户密码提示问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	@RequestMapping(value="/checkuserasw.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> checkUserAnswer(String account,String question,String asw){
		return userService.checkUserAnswer(account,question,asw);
	}
	/**
	 * 重置密码
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/resetpassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> resetPassword(Integer userId,String newPwd){
		return userService.resetPassword(userId,newPwd);
	}
	/**
	 * 修改用户个人资料
	 * @param session
	 * @param actionUserVo
	 * @return
	 */
	@RequestMapping(value="/updateuserinfo.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<User> updateUserInfo(HttpSession session, ActionUserVo actionUserVo){
		User curUser = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(curUser == null) {
			return SverResponse.createByErrorMessage("用户尚未登陆!");
		}
		actionUserVo.setId(curUser.getId());
		actionUserVo.setAccount(curUser.getAccount());
		SverResponse<User> resp = userService.updateUserInfo(actionUserVo);
		if (resp.isSuccess()) {
			//重写session
			session.setAttribute(ConstUtil.CUR_USER, resp.getData());
		}
		return resp;
	}
	/**
	 * 登录用户修改密码
	 * @param session
	 * @param newPwd
	 * @param oldPwd
	 * @return
	 */
	@RequestMapping(value="/updatepassword.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> updatePassword(HttpSession session,String newPwd,String oldPwd){
		//1.将session取出判断是否登陆
		User user = (User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("用户尚未登陆");
		}
		SverResponse<String> result = userService.updatePassword(user,newPwd,oldPwd);
		//2.将session清空,重新登录
		if(result.isSuccess()) {
			session.removeAttribute(ConstUtil.CUR_USER);
		}
		return result;
	}
	/**
	 * 用户注销登出
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/do_logout.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> doLogout(HttpSession session){
		//1.将session清空,退出登录
		session.removeAttribute(ConstUtil.CUR_USER);
		return SverResponse.createRespBySuccess();
	}
	/**
	 * 用户登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/get_user.do",method=RequestMethod.POST)
	@ResponseBody
	public User getUser(HttpSession session){
		Object user = session.getAttribute(ConstUtil.CUR_USER);
		if (user != null){
			return (User) user;
		}
		User user1 = new User();
		user1.setPassword("-1");
		return user1;
	}
	/**
	 * 注册信息监测
	 * @param info
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/do_check_info.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<String> checkInfo(String info,String type){
		return userService.checkValidation(info, type);
	}
}
