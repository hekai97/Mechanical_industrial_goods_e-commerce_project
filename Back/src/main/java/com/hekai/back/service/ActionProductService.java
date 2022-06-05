package com.hekai.back.service;

import com.hekai.back.common.SverResponse;
import com.hekai.back.pojo.ActionProduct;
import com.hekai.back.utils.PageBean;
import com.hekai.back.vo.ActionProductFloorVo;
import com.hekai.back.vo.ActionProductListVo;

import java.util.List;

public interface ActionProductService {
	/**
	 * 多条件查询商品信息
	 * @param actionProduct
	 * @return
	 */
	public SverResponse<List<ActionProductListVo>> findProducts(ActionProduct actionProduct);
	/**
	 * 保存商品信息(新增、修改)
	 * @param actionProduct
	 * @return
	 */
	public SverResponse<String> saveOrUpdate(ActionProduct actionProduct);
	/**
	 * 更新商品状态:上下架、热销
	 * @param productId
	 * @param status
	 * @param hot
	 * @return
	 */
	public SverResponse<String> updateStatus(Integer productId,Integer status,Integer hot);
	/**
	 * 前台:查找热销商品
	 * @param num	查找数量
	 * @return
	 */
	public SverResponse<List<ActionProduct>> findHotProducts(Integer num);
	/**
	 * 前台:获得首页所有楼层数据
	 * @return
	 */
	public SverResponse<ActionProductFloorVo> findFloorProducts();
	/**
	 * 前台:根据商品编号查找商品信息
	 * @param productId
	 * @return
	 */
	public SverResponse<ActionProduct> findProductDetailForPortal(Integer productId);
	/**
	 * 前台:根据产品类型和配件类型查找商品信息(模糊查询)
	 * @param productTypeId
	 * @param partsId
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public SverResponse<PageBean<ActionProductListVo>> findProductsForPortal(Integer productTypeId, Integer partsId,
																			 String name, int pageNum, int pageSize);
}
