package com.hekai.back.service.Impl;

import com.hekai.back.common.SverResponse;
import com.hekai.back.dao.ActionParamsDao;
import com.hekai.back.dao.ActionProductDao;
import com.hekai.back.pojo.ActionParam;
import com.hekai.back.pojo.ActionProduct;
import com.hekai.back.service.ActionParamsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ActionParamsServiceImpl implements ActionParamsService {

	@Autowired
	private ActionParamsDao actionParamsDao;
	
	@Autowired
	private ActionProductDao actionProductDao;

	@Override
	public SverResponse<List<ActionParam>> findAllParams() {
		// TODO 自动生成的方法存根
		//1.查找一级子节点
		List<ActionParam> paramList = actionParamsDao.findParamsByParentId(0);
		//2.递归查询所有子节点
		for(ActionParam param : paramList) {
			findDirectChildren(param);
		}
		return SverResponse.createRespBySuccess(paramList);
	}
	/**
	 * 递归查找
	 * @param parentParam
	 */
	private void findDirectChildren(ActionParam parentParam) {
		// TODO 自动生成的方法存根
		//查找子节点
		List<ActionParam> paramList = actionParamsDao.findParamsByParentId(parentParam.getId());
		parentParam.setChildren(paramList);
		for (ActionParam actionParam : paramList) {
			findDirectChildren(actionParam);
		}
	}
	
	
	
	@Override
	public SverResponse<String> addParam(ActionParam actionParam) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(actionParam.getName())) {
			return SverResponse.createByErrorMessage("参数异常！");
		}
		//判断新增的类型名在同一父类型中是否重名
		ActionParam param=actionParamsDao.findParamByParentIdAndName(actionParam.getParent_id(), actionParam.getName());
		if(param!=null) {
			return SverResponse.createByErrorMessage("商品类型名已经存在！");
		}
		//可以调用dao的方法新增类型
		actionParam.setStatus(true);
		actionParam.setCreated(new Date());
		actionParam.setUpdated(new Date());
		actionParam.setLevel(this.getParamLevel(actionParam.getParent_id()));
		int rs=actionParamsDao.insertparam(actionParam);
		if(rs>0){
			return SverResponse.createRespBySuccessMessage("新增类型成功！");
		}
		return SverResponse.createByErrorMessage("新增类型失败！");
	}
	/**
	 * 计算新增类型节点的level，实际上使它父类型level+1
	 * 如果新增的类型是根类型，那么level为0
	 * @param parentId
	 * @return
	 */
	private int getParamLevel(int parentId) {
		ActionParam param=actionParamsDao.findParamById(parentId);
		if(param!=null) {
			return param.getLevel()+1;
		}
		return 0;
	}
	@Override
	public SverResponse<String> updateParam(ActionParam actionParam) {
		// TODO Auto-generated method stub
		//1.判断参数异常
		if(actionParam.getId()==0|| StringUtils.isBlank(actionParam.getName())) {
			return SverResponse.createByErrorMessage("参数异常！");
		}
		//2.判断重名问题
		ActionParam param=actionParamsDao.findParamByParentIdAndName(actionParam.getParent_id(), actionParam.getName());
		if(param!=null) {
			return SverResponse.createByErrorMessage("商品类型名已经存在！");
		}
		//3.属性修改
		ActionParam origin=actionParamsDao.findParamById(actionParam.getId());
		origin.setName(actionParam.getName());
		origin.setUpdated(new Date());
		//4.调用Dao层方法
		int rs=actionParamsDao.updateParam(origin);
		if(rs>0){
			return SverResponse.createRespBySuccessMessage("修改类型成功！");
		}
		return SverResponse.createByErrorMessage("修改类型失败！");
	}
	@Override
	public SverResponse<String> delParam(Integer id) {
		// TODO Auto-generated method stub
		//1.判断当前类型有没有子类型
		List<ActionParam> params=actionParamsDao.findParamsByParentId(id);
		if(params.size()!=0) {
			return SverResponse.createByErrorMessage("请先删除子类型！");
		}
		//2.判断当前类型是否被商品使用
		List<ActionProduct> products=actionProductDao.findProductsByPartsId(id);
		if(products.size()!=0) {
			return SverResponse.createByErrorMessage("不能删除有商品的类型！");
		}
		//3.调用dao方法
		int rs=actionParamsDao.deleteParam(id);
		if(rs==0) {
			return SverResponse.createByErrorMessage("删除失败！");
		}
		return SverResponse.createRespBySuccessMessage("删除成功！");
	}
	@Override
	public SverResponse<List<ActionParam>> findParamChildren(Integer id) {
		// TODO Auto-generated method stub
		//调用dao层的方法
		List<ActionParam> params=actionParamsDao.findParamsByParentId(id);
		
		return SverResponse.createRespBySuccess(params);
	}

	@Override
	public SverResponse<List<ActionParam>> findPType() {
		return SverResponse.createRespBySuccess(actionParamsDao.findParamsByParentId(0));
	}

	@Override
	public SverResponse<List<ActionParam>> findPartsType(Integer productTypeId) {
		return SverResponse.createRespBySuccess(actionParamsDao.findParamsByParentId(productTypeId));
	}
}
