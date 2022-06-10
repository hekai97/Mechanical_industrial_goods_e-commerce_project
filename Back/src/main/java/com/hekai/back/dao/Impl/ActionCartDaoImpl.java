package com.hekai.back.dao.Impl;

import com.hekai.back.dao.ActionCartDao;
import com.hekai.back.pojo.ActionCart;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ActionCartDaoImpl implements ActionCartDao {
	@Autowired
	private QueryRunner queryRunner;
	
	private String str = "id,user_id,product_id,quantity,created,updated,checked";
	@Override
	public ActionCart findCartByUserAndProductId(Integer userId, Integer productId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_carts WHERE user_id = ? and product_id = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionCart>(ActionCart.class),userId,productId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Integer insertCart(ActionCart cart) {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO action_carts(user_id,product_id,quantity,created,updated) VALUES(?,?,?,?,?)";
		Object[] params = {cart.getUser_id(),cart.getProduct_id(),cart.getQuantity(),cart.getCreated(),cart.getUpdated()};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public Integer updateCartById(ActionCart actionCart) {
		// TODO 自动生成的方法存根
		String sql = "UPDATE action_carts set quantity = ?,updated = ?,checked = ? WHERE id = ?";
		Object[] params = {actionCart.getQuantity(),actionCart.getUpdated(),actionCart.getChecked(),actionCart.getId()};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ActionCart> findCartByUser(Integer userId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_carts WHERE user_id = ?";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionCart>(ActionCart.class),userId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int deleteCartByUserId(Integer userId) {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM action_carts WHERE user_id = ?";
		try {
			return queryRunner.update(sql, userId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateCartByUserIdAndProductId(ActionCart actionCart) {
		// TODO 自动生成的方法存根
		String sql = "UPDATE action_carts set quantity = ?";
		List<Object> params = new ArrayList<>();
		params.add(actionCart.getQuantity());
		if (actionCart.getChecked() != null) {
			sql += ",checked = ?";
			params.add(actionCart.getChecked());
		}
		sql += ",updated = ? WHERE user_id = ? and product_id = ?";
		params.add(actionCart.getUpdated());
		params.add(actionCart.getUser_id());
		params.add(actionCart.getProduct_id());
		try {
			return queryRunner.update(sql, params.toArray());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteCartByUserIdAndProductId(Integer userId, Integer productId) {
		// TODO 自动生成的方法存根
		String sql = "DELETE FROM action_carts WHERE user_id = ? and product_id = ?";
		try {
			return queryRunner.update(sql, userId,productId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int getCartCountByUserId(Integer userId) {
		// TODO 自动生成的方法存根
		String sql = "SELECT count(id) as num FROM action_carts WHERE user_id = ?";
		try {
			return queryRunner.query(sql, new ColumnListHandler<Long>("num"),userId).get(0).intValue();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
}
