package com.hekai.back.dao;

import com.hekai.back.pojo.User;

import java.util.List;

public interface ActionUserDao {
	/**
	 * 根据用户名查找用户
	 * @param account
	 * @return
	 */
	public int checkUserByAccount(String account);
	/**
	 * 根据用户名和密码查找用户
	 * @param account
	 * @param password
	 * @return
	 */
	public User findUserByAccountAndPassword(String account, String password);
	/**
	 * 获得所有用户信息
	 * @return
	 */
	public List<User> findAllUser();
	/**
	 * 根据用户id获得用户对象
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	/**
	 * 验证电子邮箱是否被注册
	 * @param email
	 * @return
	 */
	public int checkUserByEmail(String email);
	/**
	 * 验证电话号码是否被注册
	 * @param phone
	 * @return
	 */
	public int checkUserByPhone(String phone);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	/**
	 * 根据用户名返回用户信息
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	/**
	 * 校验用户密码答案
	 * @param account
	 * @param question
	 * @param asw
	 * @return
	 */
	public int checkUserAnswer(String account, String question, String asw);
	/**
	 * 验证用户密码是否正确
	 * @param account
	 * @param password
	 * @return
	 */
	public int checkPassword(String account, String password);
}
