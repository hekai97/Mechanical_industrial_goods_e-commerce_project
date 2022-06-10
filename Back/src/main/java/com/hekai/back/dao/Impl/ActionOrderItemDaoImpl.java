package com.hekai.back.dao.Impl;

import com.hekai.back.dao.ActionOrderItemDao;
import com.hekai.back.pojo.ActionOrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public class ActionOrderItemDaoImpl implements ActionOrderItemDao {
	@Autowired
	private QueryRunner queryRunner;

	private String str = "id,uid,order_no,goods_id,goods_name,icon_url,price,quantity,total_price,created,updated";
	@Override
	public List<ActionOrderItem> getItemsByOrderNo(Long orderNo) {
		// TODO 自动生成的方法存根
		String sql = "SELECT " + str + " FROM action_order_items WHERE order_no = ?";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionOrderItem>(ActionOrderItem.class),orderNo);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int[] batchInsert(List<ActionOrderItem> orderItems) {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO action_order_items(uid,order_no,goods_id,goods_name,icon_url,price,quantity,"
				+ "total_price,created,updated) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[][] params =	new Object[orderItems.size()][]; 
		for (int i = 0; i < orderItems.size(); i++) {
			ActionOrderItem item = orderItems.get(i);
			params[i] = new Object[] {
					item.getUid(),item.getOrder_no(),item.getGoods_id(),item.getGoods_name(),item.getIcon_url(),
					item.getPrice(),item.getQuantity(),item.getTotal_price(),item.getCreated(),item.getUpdated()
			};
		}
		try {
			return queryRunner.batch(sql, params);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
}
