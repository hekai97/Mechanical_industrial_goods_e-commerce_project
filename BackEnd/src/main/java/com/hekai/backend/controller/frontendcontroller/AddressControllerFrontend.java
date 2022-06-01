package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.Address;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
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

    @RequestMapping(value = "/findAddressById")
    public Result<Address> findAddressById(HttpSession httpSession, @RequestBody int id){
        return null;
    }

    @RequestMapping(value = "/setdefault")
    public Result<List<Address>> setDefault(HttpSession httpSession,@RequestBody int id){
        return null;
    }

    @RequestMapping(value = "/findaddrs")
    public Result<List<Address>> findAddrs(HttpSession httpSession,@RequestBody int id){

        return null;
    }

    @RequestMapping(value = "/deladdr")
    public Result<List<Address>> delAddr(HttpSession httpSession,@RequestBody int id){

        return null;
    }

    @RequestMapping(value = "/saveaddr")
    public Result<List<Address>> saveAddr(HttpSession httpSession,
                                          @RequestBody String name,
                                          String mobile,
                                          String province,
                                          String city,
                                          String district,
                                          String addr,
                                          String zip){
        return null;
    }

}
