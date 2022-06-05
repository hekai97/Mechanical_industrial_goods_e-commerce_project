package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.repository.AddressRepository;
import com.hekai.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class AddressServiceImp implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Result<Address> findAddressByAddrId(Integer id) {
        Address address=addressRepository.findAddressById(id);
        if(address==null){
            return Result.createByErrorMessage("参数错误");
        }
        return Result.createRespBySuccess(address);
    }

    @Override
    public Result<List<Address>> setDefault(User user, Integer id) {
        Address address=addressRepository.findAddressById(id);
        if(address==null){
            return Result.createByErrorMessage("默认地址修改失败");
        }
        address.setDfault(true);
        address.setUpdated(new Timestamp(new Date().getTime()));
        addressRepository.save(address);
        List<Address> addresses=addressRepository.findByUserId(user.getId());
        return Result.createRespBySuccess(addresses);
    }

    @Override
    public Result<List<Address>> findAddressByUserId(int id) {
        return Result.createRespBySuccess(addressRepository.findByUserId(id));
    }

    @Override
    public Result<List<Address>> deleteAddressById(User user, Integer id) {
        Address address=addressRepository.findAddressById(id);
        if(address==null){
            return Result.createByErrorMessage("删除失败");
        }
        address.setIsDel((byte) 1);
        address.setUpdated(new Timestamp(new Date().getTime()));
        addressRepository.save(address);
        return Result.createRespBySuccess(addressRepository.findByUserId(user.getId()));
    }

    @Override
    public Result<List<Address>> saveAddress(User user, Address address) {
        address.setIsDel((byte) 0);
        address.setDfault(false);
        Timestamp timestamp=new Timestamp(new Date().getTime());
        address.setCreated(timestamp);
        address.setUpdated(timestamp);
        address.setUserId(user.getId());
        addressRepository.save(address);
        return Result.createRespBySuccess(addressRepository.findByUserId(user.getId()));
    }
}
