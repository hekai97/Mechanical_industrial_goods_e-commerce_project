package com.hekai.back.dao;

import com.hekai.back.pojo.ActionAddress;

import java.util.List;

public interface ActionAddressDao {
	/**
	 * 根据id查询收货人地址信息
	 * @param addrId
	 * @return
	 */
	public ActionAddress findAddrsById(Integer addrId);

	/**
	 * 查询是否存在默认地址
	 * @param userId
	 * @return
	 */
	public int findDefaultAddrByUserId(Integer userId);

	/**
	 * 新增收货人地址信息
	 * @param addr
	 * @return
	 */
	public int insertAddress(ActionAddress addr);

	/**
	 * 更新收件人地址信息
	 * @param addr
	 * @return
	 */
	public int updateAddress(ActionAddress addr);

	/**
	 * 查询用户的收件人地址信息
	 * @param userid
	 * @return
	 */
	public List<ActionAddress> findAddrsByUserId(Integer userid);
	/**
	 * 读取用户默认地址
	 * @param userId
	 * @return
	 */
	public ActionAddress findDefaultAddr(Integer userId);
}
