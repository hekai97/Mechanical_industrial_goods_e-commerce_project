package com.hekai.back.dao.Impl;

import com.hekai.back.dao.ActionProductDao;
import com.hekai.back.pojo.ActionProduct;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ActionProductDaoImpl implements ActionProductDao {
	@Autowired
	private QueryRunner queryRunner;
	private String str="id,name,productId,partsId,iconUrl,subImages,"
			+ "detail,specParam,price,stock,status,hot,created,updated";
	@Override
	public Integer getTotalCount(ActionProduct actionProduct) {
		// TODO 自动生成的方法存根
		String sql="select count(id) as num from action_products where 1=1 ";
		List<Object> params=new ArrayList<>();
		if (actionProduct.getId() != null) {
			sql+=" and id = ?";
			params.add(actionProduct.getId());
		}
		if(actionProduct.getProductId() != null) {
			sql+=" and productId = ?";
			params.add(actionProduct.getProductId());
		}
		if(actionProduct.getName() != null) {
			sql+=" and name like ?";
			params.add(actionProduct.getName());
		}
		if(actionProduct.getStatus() != null) {
			sql+=" and status = ?";
			params.add(actionProduct.getStatus());
		}
		if(actionProduct.getPartsId() != null) {
			sql+=" and partsId = ?";
			params.add(actionProduct.getPartsId());
		}
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"),params.toArray())
					.get(0).intValue();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ActionProduct> findProductsNoPage(ActionProduct condition) {
		// TODO 自动生成的方法存根
		String sql="SELECT "+str+" FROM action_products where 1=1";
		List<Object> params=new ArrayList<>();
		if(condition.getId()!=null) {
			sql+=" and id=?";
			params.add(condition.getId());
		}
		if(condition.getName()!=null) {
			sql+=" and name like ?";
			params.add(condition.getName());
		}
		if(condition.getStatus()!=null) {
			sql+=" and status=?";
			params.add(condition.getId());
		}
		if(condition.getProductId()!=null) {
			sql+=" and productId=?";
			params.add(condition.getProductId());
		}
		if(condition.getPartsId()!=null) {
			sql+=" and partsId=?";
			params.add(condition.getPartsId());
		}
		if(condition.getId()!=null) {
			sql+=" and id=?";
			params.add(condition.getId());
		}
		sql+=" order by	created,id desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class),params.toArray());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int insertProduct(ActionProduct product) {
		// TODO 自动生成的方法存根
		String sql="INSERT INTO action_products(name,productId,"
				+ "partsId,iconUrl,subImages,detail,specParam,price,"
				+ "stock,status,hot,created,updated)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params= {product.getName(),product.getProductId(),product.getPartsId(),product.getIconUrl()
				,product.getSubImages(),product.getDetail(),product.getSpecParam(),product.getPrice()
				,product.getStock(),product.getStatus(),product.getHot(),product.getCreated(),product.getUpdated()};
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateProduct(ActionProduct product) {
		// TODO 自动生成的方法存根
		String sql="UPDATE action_products SET updated=?";
		List<Object> params=new ArrayList<>();
		params.add(product.getUpdated());
		if(!StringUtils.isEmpty(product.getName())) {
			sql+=",name=?";
			params.add(product.getName());
		}
		if(product.getProductId()!=null) {
			sql+=",productId=?";
			params.add(product.getProductId());
		}
		if(product.getPartsId()!=null) {
			sql+=",partsId=?";
			params.add(product.getPartsId());
		}
		if(product.getPrice()!=null) {
			sql+=",price=?";
			params.add(product.getPrice());
		}
		if(product.getStock()!=null) {
			sql+=",stock=?";
			params.add(product.getStock());
		}
		if(!StringUtils.isEmpty(product.getIconUrl())) {
			sql+=",iconUrl=?";
			params.add(product.getIconUrl());
		}
		if(!StringUtils.isEmpty(product.getSubImages())) {
			sql+=",subImages=?";
			params.add(product.getSubImages());
		}
		if(product.getStatus()!=null) {
			sql+=",status=?";
			params.add(product.getStatus());
		}
		if(!StringUtils.isEmpty(product.getDetail())) {
			sql+=",detail=?";
			params.add(product.getDetail());
		}
		if(!StringUtils.isEmpty(product.getSpecParam())) {
			sql+=",specParam=?";
			params.add(product.getSpecParam());
		}
		if(product.getHot()!=null) {
			sql+=",hot=?";
			params.add(product.getHot());
		}
		sql+=" where id=?";
		params.add(product.getId());
		try {
			return queryRunner.update(sql, params.toArray());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ActionProduct> findHotProducts(Integer num) {
		// TODO 自动生成的方法存根
		String sql="SELECT "+str+" FROM action_products where hot=1 and status=2 "
				+ "order by updated,id desc";
		if(num != null) {
			sql+=" limit 0,?";
		}
		try {
			if(num != null) {
				return queryRunner.query(sql,new BeanListHandler<ActionProduct>(ActionProduct.class),num);
			}else {
				return queryRunner.query(sql,new BeanListHandler<ActionProduct>(ActionProduct.class));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ActionProduct> findProductsByProductCategory(Integer categoryId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_products where productId=? and status=2 "
				+ "order by updated desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class),categoryId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ActionProduct findProductById(Integer id) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_products where id=?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionProduct>(ActionProduct.class),id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ActionProduct> findProducts(ActionProduct actionProduct, int startIndex, int pageSize) {
		// TODO 自动生成的方法存根
		String sql="SELECT "+str+" FROM action_products where 1=1";
		List<Object> params=new ArrayList<>();
		if (actionProduct.getId() != null) {
			sql+=" and id = ?";
			params.add(actionProduct.getId());
		}
		if(actionProduct.getProductId() != null) {
			sql+=" and productId = ?";
			params.add(actionProduct.getProductId());
		}
		if(actionProduct.getName() != null) {
			sql+=" and name like ?";
			params.add(actionProduct.getName());
		}
		if(actionProduct.getStatus() != null) {
			sql+=" and status = ?";
			params.add(actionProduct.getStatus());
		}
		if(actionProduct.getPartsId() != null) {
			sql+=" and partsId = ?";
			params.add(actionProduct.getPartsId());
		}
		sql+=" limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql,new BeanListHandler<ActionProduct>(ActionProduct.class
					),params.toArray());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ActionProduct> findProductsByPartsId(Integer partsId) {
		// TODO Auto-generated method stub
		String sql="SELECT "+str+" FROM action_products where partsId=? order by updated desc";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionProduct>(ActionProduct.class),partsId);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	
	}
	
}
