package com.hekai.back.dao.Impl;

import com.hekai.back.dao.ActionParamsDao;
import com.hekai.back.pojo.ActionParam;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class ActionParamsDaoImpl implements ActionParamsDao {
	@Autowired
	private QueryRunner queryRunner;
	@Override
	public ActionParam findParamById(Integer id) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_params WHERE id = ?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionParam>(ActionParam.class),id);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ActionParam> findParamsByParentId(Integer parentId) {
		// TODO 自动生成的方法存根
		String sql="SELECT * FROM action_params WHERE parent_id = ? order by sort_order";
		try {
			return queryRunner.query(sql, new BeanListHandler<ActionParam>(ActionParam.class),parentId);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ActionParam findParamByParentIdAndName(Integer parentId, String name) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM action_params WHERE parent_id=? and name=?";
		try {
			return queryRunner.query(sql, new BeanHandler<ActionParam>(ActionParam.class),parentId,name);		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int insertparam(ActionParam param) {
		// TODO Auto-generated method stub
		String sql="INSERT into action_params(parent_id,name,sort_order,status,level,created,updated) values(?,?,?,?,?,?,?)";
		Object[] params= {param.getParent_id(),param.getName(),param.getSort_order(),param.getStatus(),param.getLevel(),param.getCreated(),param.getUpdated()};
		try {
			return queryRunner.update(sql,params);		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int updateParam(ActionParam param) {
		// TODO Auto-generated method stub
		String sql="update action_params set parent_id=?,name=?,sort_order=?,status=?,level=?,created=?,updated=? where id=?";
		Object[] params= {param.getParent_id(),param.getName(),param.getSort_order(),
				param.getStatus(),param.getLevel(),param.getCreated(),
				param.getUpdated(),param.getId()};
		try {
			return queryRunner.update(sql,params);		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
	}
	@Override
	public int deleteParam(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from action_params where id=?";
		try {
			return queryRunner.update(sql,id);		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

}
