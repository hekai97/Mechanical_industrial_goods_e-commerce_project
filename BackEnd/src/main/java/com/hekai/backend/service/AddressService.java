package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.sourceEntites.User;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface AddressService {
    Result<Address> findAddressByAddrId(Integer id);

    Result<List<Address>> setDafault(User user, Integer id);

    Result<List<Address>> findAddressByUserId(int id);

    Result<List<Address>> deleteAddressById(User user, Integer id);

    Result<List<Address>> saveAddress(User user, Address address);
}
