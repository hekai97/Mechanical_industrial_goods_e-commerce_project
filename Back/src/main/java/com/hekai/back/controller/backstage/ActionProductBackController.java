package com.hekai.back.controller.backstage;

import com.hekai.back.common.ResponseCode;
import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionProduct;
import com.hekai.back.pojo.User;
import com.hekai.back.service.ActionProductService;
import com.hekai.back.service.ActionUserService;
import com.hekai.back.utils.ConstUtil;
import com.hekai.back.vo.ActionProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/mgr/product")
public class ActionProductBackController {
	
	@Autowired
	private ActionProductService actionProductService;
	@Autowired
	private ActionUserService userService;
	/**
	 * 查询商品信息
	 * @param session
	 * @param product
	 * @return
	 */
	@RequestMapping("/productlist.do")
	@ResponseBody
	public SverResponse<List<ActionProductListVo>> findProducts(HttpSession session, ActionProduct product){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法查询产品的信息
			return actionProductService.findProducts(product);
		}
		
		return SverResponse.createByErrorMessage("您无操作权限!");
	}
	/**
	 * 新增商品
	 * @param session
	 * @param product
	 * @return
	 */
	@RequestMapping("/saveproduct.do")
	@ResponseBody
	public SverResponse<String> saveProduct(HttpSession session,ActionProduct product){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法保存商品信息
			return actionProductService.saveOrUpdate(product);
		}
		
		return SverResponse.createByErrorMessage("您无操作权限!");
	}
	/**
	 * 修改商品状态:上下架、热销
	 * @param session
	 * @param productId
	 * @param status
	 * @param hot
	 * @return
	 */
	@RequestMapping("/setstatus.do")
	@ResponseBody
	public SverResponse<String> modifyStatus(HttpSession session,Integer productId,Integer status,Integer hot){
		//1.判断用户是否登陆
		User user=(User)session.getAttribute(ConstUtil.CUR_USER);
		if(user==null) {
			return SverResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), "请登录后再进行操作!");
		}
		//2.用户是不是管理员
		SverResponse<String> response=userService.isAdmin(user);
		if(response.isSuccess()) {
			//3.调用Service中的方法更新状态信息
			return actionProductService.updateStatus(productId, status, hot);
		}
		
		return SverResponse.createByErrorMessage("您无操作权限!");
	}
	//图片上传接口
	@RequestMapping(value = "pic_upload.do")
	@ResponseBody
	public void pictureUpLoad(@RequestBody MultipartFile image){
		String filePath="upload";
		System.out.println(image.getName()+" "+image.getContentType());
		try {
			image.transferTo(new File(filePath, Objects.requireNonNull(image.getOriginalFilename())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
