package com.hekai.backend.controller.frontendcontriller;

import com.hekai.backend.entites.Address;
import com.hekai.backend.entites.reconstruction.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/addr")
public class AddressControllerFrontend {

    @RequestMapping(value = "/findAddressById")
    public Result<Address> findAddressById(@RequestBody int id){
        return null;
    }

    @RequestMapping(value = "/setdefault")
    public Result<List<Address>> setDefault(@RequestBody int id){
        return null;
    }

    @RequestMapping(value = "/findaddrs")
    public Result<List<Address>> findAddrs(@RequestBody int id){

        return null;
    }

    @RequestMapping(value = "/deladdr")
    public Result<List<Address>> delAddr(@RequestBody int id){

        return null;
    }

    @RequestMapping(value = "/saveaddr")
    public Result<List<Address>> saveAddr(@RequestBody String name,
                                          String mobile,
                                          String province,
                                          String city,
                                          String district,
                                          String addr,
                                          String zip){
        return null;
    }

}
