package com.hekai.back.service.Impl;

import com.google.common.collect.Lists;
import com.hekai.back.common.SverResponse;
import com.hekai.back.dao.ActionOrderDao;
import com.hekai.back.dao.ActionUserDao;
import com.hekai.back.pojo.User;
import com.hekai.back.service.ActionUserService;
import com.hekai.back.utils.ConstUtil;
import com.hekai.back.utils.MD5Util;
import com.hekai.back.utils.TokenCache;
import com.hekai.back.vo.ActionUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ActionUserServiceImpl implements ActionUserService {
	
	@Autowired
	private ActionUserDao actionUserDao;
	@Autowired
	private ActionOrderDao actionOrderDao;
	@Override
	public SverResponse<User> doLogin(String account, String password) {
		// TODO 自动生成的方法存根
		//1.判断用户名是否存在
		int rs=actionUserDao.checkUserByAccount(account);
		if(rs==0) {
			return SverResponse.createByErrorMessage("用户不存在!");
		}
		//2.根据用户名和密码查找用户
		String md5pwd= MD5Util.MD5Encode(password, "utf-8", false);
//		System.out.println(md5pwd);
		User user=actionUserDao.findUserByAccountAndPassword(account, md5pwd);
		//3.判断查找用户密码是否正确
		if(user==null) {
			return SverResponse.createByErrorMessage("密码错误!");
		}
		//置空密码
		user.setPassword(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess("登录成功！", user);
	}
	@Override
	public SverResponse<String> isAdmin(User user) {
		// TODO 自动生成的方法存根
		if(user.getRole()==ConstUtil.Role.ROLE_ADMIN) {
			return SverResponse.createRespBySuccess();
		}
		return SverResponse.createRespByError();
	}
	@Override
	public SverResponse<List<ActionUserVo>> findUserList() {
		// TODO 自动生成的方法存根
		List<ActionUserVo> vos=Lists.newArrayList();
		//1.调用dao层类的方法
		List<User> users=actionUserDao.findAllUser();
		//2.处理:user对象转换成ActionUserVo对象
		for(User u:users) {
			vos.add(setNormalProperty(u));
		}
		return SverResponse.createRespBySuccess(vos);
	}
	/**
	 * 将user转换成actionUserVo对象
	 * @param user
	 * @return
	 */
	private ActionUserVo setNormalProperty(User user) {
		ActionUserVo vo=new ActionUserVo();
		vo.setAccount(user.getAccount());
		vo.setAge(user.getAge());
		vo.setEmail(user.getEmail());
		vo.setId(user.getId());
		vo.setName(user.getName());
		vo.setPhone(user.getPhone());
		if(user.getSex()==1) {
			vo.setSex("男");
		}else {
			vo.setSex("女");
		}
		return vo;
	}
	@Override
	public SverResponse<ActionUserVo> findUserById(Integer id) {
		// TODO 自动生成的方法存根
		//1.调用dao层类中的方法获得用户对象
		User user=actionUserDao.findUserById(id);
		//2.将user转化成actionUserVo对象
		ActionUserVo vo=setNormalProperty(user);
		return SverResponse.createRespBySuccess(vo);
	}
	@Override
	public SverResponse<User> updateUserInfo(ActionUserVo actionUserVo) {
		// TODO 自动生成的方法存根
		//1.根据id获得user对象
		User user=actionUserDao.findUserById(actionUserVo.getId());
		//2.把userVo里修改的属性值赋给user对象
		user.setAccount(actionUserVo.getAccount());
		user.setAge(actionUserVo.getAge());
		user.setEmail(actionUserVo.getEmail());
		user.setName(actionUserVo.getName());
		user.setPhone(actionUserVo.getPhone());
		if(actionUserVo.getSex().equals("男")) {
			user.setSex(1);
		}else {
			user.setSex(0);
		}
		user.setUpdate_time(new Date());
		//3.调用dao层的方法
		int rs=actionUserDao.updateUserInfo(user);
		if(rs>0) {
			return SverResponse.createRespBySuccess("用户信息修改成功!",user);
		}
		return SverResponse.createByErrorMessage("用户信息修改失败!");
	}
	@Override
	public SverResponse<String> delUser(Integer id) {
		// TODO 自动生成的方法存根
		//1.判断是否存在订单
		if(actionOrderDao.findOrderByUid(id).size()>0) {
			return SverResponse.createByErrorMessage("用户存在关联的订单，无法删除!");
		}
		//2.没有,修改del为1
		User user=actionUserDao.findUserById(id);
		user.setDel(1);
		user.setUpdate_time(new Date());
		int rs=actionUserDao.updateUserInfo(user);
		if(rs>0) {
			return SverResponse.createRespBySuccessMessage("用户删除成功!");
		}
		return SverResponse.createByErrorMessage("用户删除失败!");
	}
	@Override
	public SverResponse<String> doRegister(User user) {
		// TODO 自动生成的方法存根
		//1.检查用户名是否存在
		SverResponse<String> resp = checkValidation(user.getAccount(), ConstUtil.TYPE_ACCOUNT);
		if(!resp.isSuccess()) {
			return resp;
		}
		//2.检查邮箱是否被注册
		resp = checkValidation(user.getEmail(), ConstUtil.TYPE_EMAIL);
		if(!resp.isSuccess()) {
			return resp;
		}
		//3.指定用户角色,通过前端注册的用户都为客户
		user.setRole(ConstUtil.Role.ROLE_CUSTOMER);
		//4.对密码进行加密
		user.setPassword(MD5Util.MD5Encode(user.getPassword(), "utf-8" , false));
		//5.执行注册
		Date curDate=new Date();
		user.setCreate_time(curDate);
		user.setUpdate_time(curDate);
		int rs = actionUserDao.insertUser(user);
		if(rs == 0) {
			return SverResponse.createByErrorMessage("注册失败!");
		}
		return SverResponse.createRespBySuccessMessage("注册成功!");
	}
	@Override
	public SverResponse<String> checkValidation(String str, String type) {
		// TODO 自动生成的方法存根
		//1.判断type不为空
		if(StringUtils.isNotBlank(type)) {
			if (ConstUtil.TYPE_ACCOUNT.equals(type)) {
				int rs = actionUserDao.checkUserByAccount(str);
				if (rs > 0) {
					return SverResponse.createByErrorMessage("用户名已存在!");
				}
			}
			if (ConstUtil.TYPE_EMAIL.equals(type)) {
				int rs = actionUserDao.checkUserByEmail(str);
				if(rs > 0) {
					return SverResponse.createByErrorMessage("邮箱已被注册!");
				}
			}
			if (ConstUtil.TYPE_PHONE.equals(type)) {
				int rs = actionUserDao.checkUserByPhone(str);
				if(rs > 0) {
					return SverResponse.createByErrorMessage("电话号码已被注册!");
				}
			}
		}else {
			return SverResponse.createByErrorMessage("信息验证类别错误!");
		}
		return SverResponse.createRespBySuccessMessage("信息验证成功!");
	}
	@Override
	public SverResponse<User> findUserByAccount(String account) {
		// TODO 自动生成的方法存根
		//1.通过用户名查找到用户
		User user=actionUserDao.findUserByAccount(account);
		if(user==null) {
			return SverResponse.createByErrorMessage("用户名不存在!");
		}
		//2.将密码置空
		user.setPassword(StringUtils.EMPTY);
		//3.将安全问题答案置空
		user.setAsw(StringUtils.EMPTY);
		return SverResponse.createRespBySuccess(user);
	}
	@Override
	public SverResponse<String> checkUserAnswer(String account, String question, String asw) {
		// TODO 自动生成的方法存根
		//1.获取校验结果
		int rs=actionUserDao.checkUserAnswer(account,question,asw);
		if(rs>0) {
			//2.答案正确,生成token
			String token = UUID.randomUUID().toString();
			//放入缓存
			TokenCache.setCacheData(TokenCache.PREFIX+account, token);
			return SverResponse.createRespBySuccessMessage(token);
		}
		return SverResponse.createByErrorMessage("问题答案错误!");
		
	}
	@Override
	public SverResponse<String> resetPassword(Integer userId, String newPwd) {
		// TODO 自动生成的方法存根
		//1.将密码加密
		String md5pwd = MD5Util.MD5Encode(newPwd, "utf-8", false);
		//2.获得user对象
		User user = actionUserDao.findUserById(userId);
		//3.更新密码
		user.setPassword(md5pwd);
		user.setUpdate_time(new Date());
		int rs=actionUserDao.updateUserInfo(user);
		if (rs>0) {
			return SverResponse.createRespBySuccessMessage("密码重置成功!");
		}
		return SverResponse.createByErrorMessage("密码重置失败!");
	}
	@Override
	public SverResponse<String> updatePassword(User user, String newPwd, String oldPwd) {
		// TODO 自动生成的方法存根
		//1.防止越权，检测用户旧密码是否正确
		String md5OldPwd = MD5Util.MD5Encode(oldPwd, "utf-8", false);
		int rs = actionUserDao.checkPassword(user.getAccount(),md5OldPwd);
		if (rs == 0) {
			return SverResponse.createByErrorMessage("原始密码错误!");
		}
		//2.正确，将新密码更新
		String md5NewPwd = MD5Util.MD5Encode(newPwd, "utf-8", false);
		user.setPassword(md5NewPwd);
		user.setUpdate_time(new Date());
		rs = actionUserDao.updateUserInfo(user);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("密码修改成功!");
		}
		return SverResponse.createByErrorMessage("密码修改失败!");
	}
	@Override
	public SverResponse<String> getUserQusetion(String account) {
		// TODO 自动生成的方法存根
		//1.判断用户名是否存在
		//1.通过用户名查找到用户
		User user=actionUserDao.findUserByAccount(account);
		if(user==null) {
			return SverResponse.createByErrorMessage("用户名不存在!");
		}
		//2.判断用户是否设置密码问题
		if (!user.getQuestion().isEmpty() && user.getQuestion() != null) {
			return SverResponse.createRespBySuccessMessage(user.getQuestion());
		}
		return SverResponse.createByErrorMessage("未设置密码提示问题!");
	}
	
}
