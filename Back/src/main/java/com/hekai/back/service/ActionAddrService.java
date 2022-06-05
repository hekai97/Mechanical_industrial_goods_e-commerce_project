package com.hekai.back.service;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionAddress;

import java.util.List;

public interface ActionAddrService {

	/**
	 * 新增收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> addAddress(ActionAddress addr);

	/**
	 * 更新收件人地址信息
	 * @param addr
	 * @return
	 */
	public SverResponse<String> updateAddress(ActionAddress addr);

	/**
	 * 查找某个用户的所有收货地址
	 * @param userid
	 * @return
	 */
	public SverResponse<List<ActionAddress>> findAddrsByUserId(Integer userid);
	/**
	 * 根据id删除收件人地址信息
	 * @param userId
	 * @param id
	 * @return
	 */
	public SverResponse<String> delAddress(Integer userId, Integer id);
	/**
	 * 更新默认地址
	 * @param userId
	 * @param id
	 * @return
	 */
	public SverResponse<String> updateAddrDefaultStatus(Integer userId, Integer id);

}
