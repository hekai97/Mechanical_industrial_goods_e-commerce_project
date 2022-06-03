package com.hekai.back.service.Impl;

import com.hekai.back.common.SverResponse;
import com.hekai.back.dao.ActionAddressDao;
import com.hekai.back.pojo.ActionAddress;
import com.hekai.back.service.ActionAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ActionAddrServiceImpl implements ActionAddrService {

	@Autowired
	private ActionAddressDao aAddressDao;

	@Override
	public SverResponse<String> addAddress(ActionAddress addr) {
		// TODO Auto-generated method stub
		if(addr==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		int count=aAddressDao.findDefaultAddrByUserId(addr.getUser_id());
		if(count==0) {
			addr.setDefault_addr(1);
		}else {
			addr.setDefault_addr(0);
		}
		addr.setCreated(new Date());
		addr.setUpdated(new Date());
		int rs=aAddressDao.insertAddress(addr);
		if(rs>0) {
			return SverResponse.createRespBySuccessMessage("地址新增成功！");
		}
		return SverResponse.createByErrorMessage("地址新增失败！");
	}

	@Override
	public SverResponse<String> updateAddress(ActionAddress addr) {
		// TODO Auto-generated method stub
		if(addr==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		addr.setUpdated(new Date());
		int rs=aAddressDao.updateAddress(addr);
		if(rs>0) {
			return SverResponse.createRespBySuccessMessage("地址更新成功！");
		}
		return SverResponse.createByErrorMessage("地址更新失败！");
	}

	@Override
	public SverResponse<List<ActionAddress>> findAddrsByUserId(Integer userid) {
		// TODO Auto-generated method stub
		if(userid==null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		List<ActionAddress> list=aAddressDao.findAddrsByUserId(userid);
		return SverResponse.createRespBySuccess(list);
	}
	@Override
	public SverResponse<String> delAddress(Integer userId, Integer id) {
		// TODO 自动生成的方法存根
		//1.判断参数
		if(id == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//2.删除地址,对del_state字段做更新
		ActionAddress address = new ActionAddress();
		address.setId(id);
		address.setDel_state(1);
		address.setUpdated(new Date());
		int rs = aAddressDao.updateAddress(address);
		if (rs > 0) {
			return SverResponse.createRespBySuccessMessage("地址删除成功!");
		}
		return SverResponse.createByErrorMessage("地址删除失败!");
	}

	@Override
	public SverResponse<String> updateAddrDefaultStatus(Integer userId, Integer id) {
		// TODO 自动生成的方法存根
		//1.判断参数
		if(id == null || userId == null) {
			return SverResponse.createByErrorMessage("参数错误！");
		}
		//2.读取原来默认地址
		ActionAddress oldAddr = aAddressDao.findDefaultAddr(userId);
		//3.判断原来是否存在默认地址
		if (oldAddr != null) {
			//4.取消默认地址
			oldAddr.setDefault_addr(0);
			oldAddr.setUpdated(new Date());
			if (aAddressDao.updateAddress(oldAddr) <= 0) {
				return SverResponse.createByErrorMessage("默认地址设置失败!");
			}
		}
		ActionAddress newAddr = new ActionAddress();
		newAddr.setDefault_addr(1);
		newAddr.setId(id);
		newAddr.setUpdated(new Date());
		if (aAddressDao.updateAddress(newAddr) <= 0) {
			return SverResponse.createByErrorMessage("默认地址设置失败!");
		}
		return SverResponse.createRespBySuccessMessage("默认地址设置成功!");
	}

}
