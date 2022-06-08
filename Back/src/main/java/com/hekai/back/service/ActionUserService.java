package com.hekai.back.service;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.User;
import com.hekai.back.vo.ActionUserVo;

import java.util.List;

public interface ActionUserService {
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return
	 */
	public SverResponse<User> doLogin(String account, String password);
	/**
	 * 判断用户是否是管理员
	 * @param user
	 * @return
	 */
	public SverResponse<String> isAdmin(User user);
	/**
	 * 获得所有的用户信息
	 * @return
	 */
	public SverResponse<List<ActionUserVo>> findUserList();
	/**
	 * 根据用户id获得用户对象
	 * @param id
	 * @return
	 */
	public SverResponse<ActionUserVo> findUserById(Integer id);
	/**
	 * 更新用户信息
	 * @param actionUserVo
	 * @return
	 */
	public SverResponse<User> updateUserInfo(ActionUserVo actionUserVo);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public SverResponse<String> delUser(Integer id);
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public SverResponse<String> doRegister(User user);
	/**
	 * 信息校验验证
	 * @param str
	 * @param type
	 * @return
	 */
	public SverResponse<String> checkValidation(String str,String type);
	/**
	 * 根据用户名获得用户对象
	 * @param account
	 * @return
	 */
	public SverResponse<User> findUserByAccount(String account);
	/**
	 * 校验用户问题答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public SverResponse<String> checkUserAnswer(String account, String question, String asw);
	/**
	 * 重置密码
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	public SverResponse<String> resetPassword(Integer userId, String newPwd);
	/**
	 * 修改密码
	 * @param user
	 * @param newPwd
	 * @param oldPwd
	 * @return
	 */
	public SverResponse<String> updatePassword(User user, String newPwd, String oldPwd);
	/**
	 * 得到用户密码问题
	 * @param account
	 * @return
	 */
	public SverResponse<String> getUserQusetion(String account);
}
