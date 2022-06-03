package com.hekai.back.service.Impl;

import com.google.common.collect.Lists;
import com.hekai.back.common.SverResponse;
import com.hekai.back.dao.ActionParamsDao;
import com.hekai.back.dao.ActionProductDao;
import com.hekai.back.pojo.ActionProduct;
import com.hekai.back.service.ActionProductService;
import com.hekai.back.utils.ConstUtil;
import com.hekai.back.utils.PageBean;
import com.hekai.back.vo.ActionProductFloorVo;
import com.hekai.back.vo.ActionProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ActionProductServiceImpl implements ActionProductService {
	@Autowired
	private ActionProductDao actionProductDao;
	@Autowired
	private ActionParamsDao actionParamsDao;
	@Override
	public SverResponse<List<ActionProductListVo>> findProducts(ActionProduct actionProduct) {
		// TODO 自动生成的方法存根
		if(actionProduct.getName()!=null) {
			actionProduct.setName("%"+actionProduct.getName()+"%");
		}
		//1.调用dao层类中的方法查询
		List<ActionProduct> products=actionProductDao.findProductsNoPage(actionProduct);
		//2.将actionProduct对象转换为actionProductListVo对象
		List<ActionProductListVo> voList=Lists.newArrayList();
		for(ActionProduct product:products) {
			voList.add(createProductListVo(product));
		}
		return SverResponse.createRespBySuccess(voList);
	}
	/**
	 * 封装vo对象
	 * @param actionProduct
	 * @return
	 */
	private ActionProductListVo createProductListVo(ActionProduct actionProduct) {
		ActionProductListVo vo = new ActionProductListVo();
		vo.setId(actionProduct.getId());
		vo.setName(actionProduct.getName());
		vo.setPrice(actionProduct.getPrice());
		vo.setIcon_url(actionProduct.getIcon_url());
		vo.setStatus(actionProduct.getStatus());
		vo.setIs_hot(actionProduct.getIs_hot());
		//处理特殊属性
		vo.setStatusDesc(ConstUtil.ProductStatus.getStatusDesc(actionProduct.getStatus()));
		
		vo.setHotStatus(ConstUtil.HotStatus.getHotDesc(actionProduct.getIs_hot()));
		
		vo.setProductCategory(actionParamsDao.findParamById(actionProduct.getProduct_id()).getName());
		
		vo.setPartsCategory(actionParamsDao.findParamById(actionProduct.getParts_id()).getName());
		
		return vo;
	}
	@Override
	public SverResponse<String> updateStatus(Integer productId, Integer status, Integer hot) {
		// TODO 自动生成的方法存根
		if(productId==null||status==null||hot==null) {
			return SverResponse.createByErrorMessage("参数非法");
		}
		ActionProduct actionProduct=new ActionProduct();
		actionProduct.setId(productId);
		actionProduct.setUpdated(new Date());
		//判断修改上下架还是修改热销
		if(status==-1) {
			actionProduct.setIs_hot(hot);
		}else if(hot==-1) {
			actionProduct.setStatus(status);
		}
		//调用dao层类中的方法更新商品信息
		int rs=actionProductDao.updateProduct(actionProduct);
		if(rs>0) {
			return SverResponse.createRespBySuccessMessage("修改商品状态成功!");
		}
		return SverResponse.createByErrorMessage("修改商品状态失败!");
	}
	@Override
	public SverResponse<String> saveOrUpdate(ActionProduct actionProduct) {
		// TODO 自动生成的方法存根
		if(actionProduct==null) {
			return SverResponse.createByErrorMessage("参数非法");
		}
		//1.处理主图和子图的链接，从前端传递过来的sub_images里主图分离
		//第一个链接作为主图链接，其他作为子图链接
		//修改时:如果重新上传了图片,会清空原来的，新的链接处理和新增相同
		//修改时:如果没有重新上传图片，
		if(!StringUtils.isEmpty(actionProduct.getSub_images())) {
			String[] array=actionProduct.getSub_images().split(",");
			//拿出第一个元素作为主图
			actionProduct.setIcon_url(array[0]);
			//剩下的作为子图
			String temp=actionProduct.getSub_images();
			int index=temp.indexOf(",");
			if(index!=-1) {
				if(temp.substring(index+1).equals("null")) {
					actionProduct.setSub_images(null);
				}else {
					actionProduct.setSub_images(temp.substring(index+1));
				}
			}else {
				actionProduct.setSub_images(null);
			}
		}
		//判断是新增还是修改
		if(actionProduct.getId()!=null) {
			actionProduct.setUpdated(new Date());
			//调用dao层类中的方法修改商品信息
			int rs=actionProductDao.updateProduct(actionProduct);
			if(rs>0) {
				return SverResponse.createRespBySuccessMessage("商品修改成功!");
			}
			return SverResponse.createByErrorMessage("商品修改失败!");
		}else {
			//2.处理其他的属性
			actionProduct.setStatus(ConstUtil.ProductStatus.STATUS_NEW);
			actionProduct.setIs_hot(ConstUtil.HotStatus.NORMAL_STATUS);
			actionProduct.setCreated(new Date());
			actionProduct.setUpdated(new Date());
			//3.调用dao层类中的方法新增商品信息
			int rs=actionProductDao.insertProduct(actionProduct);
			if(rs>0) {
				return SverResponse.createRespBySuccessMessage("商品新增成功!");
			}
			return SverResponse.createByErrorMessage("商品新增失败!");
		}
	}
	@Override
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num) {
		// TODO 自动生成的方法存根
		//直接查询所需数据
		List<ActionProduct> actionProducts = actionProductDao.findHotProducts(num);
		return SverResponse.createRespBySuccess(actionProducts);
	}
	@Override
	public SverResponse<ActionProductFloorVo> findFloorProducts() {
		// TODO 自动生成的方法存根
		//1.创建vo对象
		ActionProductFloorVo actionProductFloorVo = new ActionProductFloorVo();
		//2.各楼数据
		List<ActionProduct> products1 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_HNTJX);
		List<ActionProduct> products2 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_JZQZJJX);
		List<ActionProduct> products3 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_GCQZJJX);
		List<ActionProduct> products4 = actionProductDao.findProductsByProductCategory(ConstUtil.ProductType.TYPE_LMJX);
		actionProductFloorVo.setOneFloor(products1);
		actionProductFloorVo.setTwoFloor(products2);
		actionProductFloorVo.setThreeFloor(products3);
		actionProductFloorVo.setFourFloor(products4);
		return SverResponse.createRespBySuccess(actionProductFloorVo);
	}
	@Override
	public SverResponse<ActionProduct> findProductDetailForPortal(Integer productId) {
		// TODO 自动生成的方法存根
		//1.判断商品编号是否为空
		if(productId == null) {
			return SverResponse.createByErrorMessage("商品编号不能为空!");
		}
		//2.查询商品详情
		ActionProduct actionProduct=actionProductDao.findProductById(productId);
		//3.判断商品是否下架
		if(actionProduct == null) {
			return SverResponse.createByErrorMessage("产品不存在!");
		}
		if(actionProduct.getStatus()==ConstUtil.ProductStatus.STATUS_OFF_SALE) {
			return SverResponse.createByErrorMessage("产品已经下架!");
		}
		return SverResponse.createRespBySuccess(actionProduct);
	}
	@Override
	public SverResponse<PageBean<ActionProductListVo>> findProductsForPortal(Integer productTypeId, Integer partsId,
			String name, int pageNum, int pageSize) {
		// TODO 自动生成的方法存根
		//1.创建对象
		ActionProduct actionProduct=new ActionProduct();
		Integer totalRecord = 0;
		//2.判断name是否为空(模糊查询)
		if(name != null && !name.equals("")) {
			actionProduct.setName("%"+name+"%");
		}
		if(productTypeId != 0) {
			actionProduct.setProduct_id(productTypeId);
		}
		if(partsId != 0) {
			actionProduct.setParts_id(partsId);
		}
		//3.前端显示商品都为在售
		actionProduct.setStatus(2);
		//4.查找符合条件的总记录数
		totalRecord = actionProductDao.getTotalCount(actionProduct);
		//5.创建分页对象
		PageBean<ActionProductListVo> pageBean = new PageBean<>(pageNum, pageSize, totalRecord);
		//6.读取数据
		List<ActionProduct> products = actionProductDao.findProducts(actionProduct,pageBean.getStartIndex(),pageSize);
		//7.封装vo
		List<ActionProductListVo> voList=Lists.newArrayList();
		for(ActionProduct product:products) {
			voList.add(createProductListVo(product));
		}
		pageBean.setData(voList);
		return SverResponse.createRespBySuccess(pageBean);
	}
}
