package com.hekai.back.dao;

import com.hekai.back.pojo.ActionProduct;

import java.util.List;

public interface ActionProductDao {
	/**
	 * 根据条件查询商品总数
	 * @param actionProduct
	 * @return
	 */
	public Integer getTotalCount(ActionProduct actionProduct);
	/**
	 * 多条件查询商品信息
	 * @param condition
	 * @return
	 */
	public List<ActionProduct> findProductsNoPage(ActionProduct condition);
	/**
	 * 新增商品
	 * @param product
	 * @return
	 */
	public int insertProduct(ActionProduct product);
	/**
	 * 修改商品
	 * @param product
	 * @return
	 */
	public int updateProduct(ActionProduct product);
	/**
	 * 查找热销商品
	 * @param num
	 * @return
	 */
	public List<ActionProduct> findHotProducts(Integer num);
	/**
	 * 根据产品类型查询商品信息
	 * @param categoryId
	 * @return
	 */
	public List<ActionProduct> findProductsByProductCategory(Integer categoryId);
	/**
	 * 根据商品编号查询商品信息
	 * @param id
	 * @return
	 */
	public ActionProduct findProductById(Integer id);
	/**
	 * 根据条件分页查询
	 * @param actionProduct
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<ActionProduct> findProducts(ActionProduct actionProduct, int startIndex, int pageSize);
	/**
	 * 根据配件类型Id查找商品信息
	 * @param partsId
	 * @return
	 */
	public List<ActionProduct> findProductsByPartsId(Integer partsId);
}
