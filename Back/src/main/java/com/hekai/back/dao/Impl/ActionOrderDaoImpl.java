package com.hekai.back.dao.Impl;

import com.hekai.back.dao.ActionOrderDao;
import com.hekai.back.pojo.ActionOrder;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActionOrderDaoImpl implements ActionOrderDao {

	@Autowired
	private QueryRunner queryRunner;
	
	private String str = "id,order_no,uid,addr_id,amount,type,freight,"
			+ "status,payment_time,delivery_time,finish_time,close_time,updated,created";
	@Override
	public List<ActionOrder> findOrderByUid(Integer uid) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_orders WHERE uid=?";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionOrder>(ActionOrder.class),uid);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public int getTotalRecord(Integer userId, Integer status) {
		// TODO 自动生成的方法存根
		String sql="SELECT count(id) as num FROM action_orders WHERE uid=?";
		List<Object> params = new ArrayList<>();
		params.add(userId);
		if (status != 0) {
			sql += " and status = ?";
			params.add(status);
		}
		try {
			return queryRunner.query(sql,new ColumnListHandler<Long>("num"),params.toArray()).get(0).intValue();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ActionOrder> findOrders(Integer userId, Integer status, int startIndex, int pageSize) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_orders WHERE uid=?";
		List<Object> params = new ArrayList<>();
		params.add(userId);
		if (status != 0) {
			sql += " and status = ?";
			params.add(status);
		}
		sql += " limit ?,?";
		params.add(startIndex);
		params.add(pageSize);
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionOrder>(ActionOrder.class),params.toArray());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}	
	}
	@Override
	public ActionOrder findOrderByUserAndOrderNo(Integer userId, Long orderNo) {
		// TODO 自动生成的方法存根
		String sql = "SELECT "+str+" FROM action_orders WHERE uid = ? and order_no = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionOrder>(ActionOrder.class),userId,orderNo);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}	
	}
	@Override
	public int updateOrder(ActionOrder updateOrder) {
		// TODO 自动生成的方法存根
		String sql = "UPDATE action_orders set updated = ?";
		List<Object> params = new ArrayList<>();
		params.add(updateOrder.getUpdated());
		if (updateOrder.getStatus() != null) {
			sql += ",status = ?";
			params.add(updateOrder.getStatus());
		}
		if (updateOrder.getPayment_time() != null) {
			sql += ",payment_time = ?";
			params.add(updateOrder.getPayment_time());
		}
		if (updateOrder.getDelivery_time() != null) {
			sql += ",delivery_time = ?";
			params.add(updateOrder.getDelivery_time());
		}
		if (updateOrder.getFinish_time() != null) {
			sql += ",finish_time = ?";
			params.add(updateOrder.getFinish_time());
		}
		if (updateOrder.getClose_time() != null) {
			sql += ",close_time = ?";
			params.add(updateOrder.getClose_time());
		}
		sql += " WHERE id = ?";
		params.add(updateOrder.getId());
		try {
			return queryRunner.update(sql,params.toArray());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int insertOrder(ActionOrder order) {
		// TODO 自动生成的方法存根
		String sql = "INSERT INTO action_orders(order_no,uid,addr_id,amount,type,freight,status"
				+ ",payment_time,delivery_time,finish_time,close_time,updated,created)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {order.getOrder_no(),order.getUid(),order.getAddr_id(),order.getAmount(),
				order.getType(),order.getFreight(),order.getStatus(),order.getPayment_time(),
				order.getDelivery_time(),order.getFinish_time(),order.getClose_time(),order.getUpdated(),
				order.getCreated()};
		try {
			return queryRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<ActionOrder> searchOrders(Long orderNo) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM action_orders WHERE 1=1";
		try {
			if(orderNo!=null) {
				sql+=" and order_no=? order by created";
				return queryRunner.query(sql, new BeanListHandler<ActionOrder>(ActionOrder.class),orderNo);
			}
			return queryRunner.query(sql, new BeanListHandler<ActionOrder>(ActionOrder.class));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}	
	}
	@Override
	public ActionOrder findOrderDetailByOrderNo(Long orderNo) {
		// TODO 自动生成的方法存根
		String sql = "SELECT * FROM action_orders WHERE order_no = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionOrder>(ActionOrder.class),orderNo);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}		
	}


}
