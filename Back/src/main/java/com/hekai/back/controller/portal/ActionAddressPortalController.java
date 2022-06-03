package com.hekai.back.controller.portal;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionAddress;
import com.hekai.back.pojo.User;
import com.hekai.back.service.ActionAddrService;
import com.hekai.back.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/addr")
public class ActionAddressPortalController {

	@Autowired
	private ActionAddrService aAddrService;
	/**
	 * 新增地址
	 * @param session
	 * @param addr
	 * @return
	 */
	@RequestMapping(value="/saveaddr.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<ActionAddress>> saveAddress(HttpSession session, ActionAddress addr){
		User user=(User) session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorMessage("请登录后在进行操作！");
		}
		addr.setUser_id(user.getId());
		
		SverResponse<String> result=null;
		if(addr.getId()==null) {
			result=aAddrService.addAddress(addr);
		}else {
			result=aAddrService.updateAddress(addr);
		}
		if(result.isSuccess()) {
			return aAddrService.findAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
		
	}
	/**
	 * 删除地址
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteaddress.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<ActionAddress>> deleteAddress(HttpSession session,Integer id){
		User user=(User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后在进行操作！");
		}
		//隐性删除地址
		SverResponse<String> result = aAddrService.delAddress(user.getId(),id);
		//删除成功后,返回当前用户所有地址
		if (result.isSuccess()) {
			return aAddrService.findAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
	}
	/**
	 * 设置默认地址
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/setdefault.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<ActionAddress>> setDefault(HttpSession session,Integer id){
		User user=(User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后在进行操作！");
		}
		SverResponse<String> result = aAddrService.updateAddrDefaultStatus(user.getId(),id);
		if (result.isSuccess()) {
			return aAddrService.findAddrsByUserId(user.getId());
		}
		return SverResponse.createByErrorMessage(result.getMsg());
	}
	/**
	 * 查找登录用户的所有地址信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/findaddrs.do",method=RequestMethod.POST)
	@ResponseBody
	public SverResponse<List<ActionAddress>> findAddrs(HttpSession session){
		User user=(User) session.getAttribute(ConstUtil.CUR_USER);
		if(user == null) {
			return SverResponse.createByErrorMessage("请登录后在进行操作！");
		}
		return aAddrService.findAddrsByUserId(user.getId());
	}
	
}
