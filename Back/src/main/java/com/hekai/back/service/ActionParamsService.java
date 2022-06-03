package com.hekai.back.service;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionParam;

import java.util.List;

public interface ActionParamsService {
	/**
	 * 获取全部分类信息
	 * @return
	 */
	public SverResponse<List<ActionParam>> findAllParams();

	/**
	 * 新增类型
	 * @return
	 */
	public SverResponse<String> addParam(ActionParam actionParam);
	/**
	 * 修改类型
	 * @param actionParam
	 * @return
	 */
	public SverResponse<String> updateParam(ActionParam actionParam);
	/**
	 * 删除指定类型
	 * @param id
	 * @return
	 */
	public SverResponse<String> delParam(Integer id);
	/**
	 * 根据父类型查找子类型
	 * @param id
	 * @return
	 */
	public SverResponse<List<ActionParam>> findParamChildren(Integer id);
}
