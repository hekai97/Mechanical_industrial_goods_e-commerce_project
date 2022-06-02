package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.service.AddressService;
import com.hekai.backend.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/addr")
public class AddressControllerFrontend {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/findAddressById")
    public Result<Address> findAddressById(HttpSession httpSession,Integer id){
        return addressService.findAddressByAddrId(id);
    }

    @RequestMapping(value = "/setdefault")
    public Result<List<Address>> setDefault(HttpSession httpSession,Integer id){
        User user= (User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return addressService.setDafault(user,id);
    }

    @RequestMapping(value = "/findaddrs")
    public Result<List<Address>> findAddrs(HttpSession httpSession){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return addressService.findAddressByUserId(user.getId());
    }

    @RequestMapping(value = "/deladdr")
    public Result<List<Address>> delAddr(HttpSession httpSession,Integer id){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return addressService.deleteAddressById(user,id);
    }

    @RequestMapping(value = "/saveaddr")
    public Result<List<Address>> saveAddr(HttpSession httpSession,Address address){
        User user=(User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(user==null){
            return Result.createByErrorMessage("请登录后再操作");
        }
        return addressService.saveAddress(user,address);
    }

}
