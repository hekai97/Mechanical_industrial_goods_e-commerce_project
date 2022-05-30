package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/user")
public class UserControllerFrontend {
    @RequestMapping(value = "/getUserByAccount")
    public Result<User> getUserByAccount(@RequestBody String account){
        return null;
    }

    @RequestMapping(value = "/do_logout")
    public Result<User> doLogOut(@RequestBody int userId){
        return null;
    }

    @RequestMapping(value = "/updateuserinfo")
    public Result<User> updateUserInfo(@RequestBody String email,
                                       @RequestBody String phone,
                                       @RequestBody String question,
                                       @RequestBody String asw,
                                       @RequestBody int age,
                                       @RequestBody byte sex){
        return null;
    }

    @RequestMapping(value = "/updatepassword")
    public Result<User> updatePassword(@RequestBody int userId,@RequestBody String nwepwd,@RequestBody String oldpwd){

        return null;
    }

    @RequestMapping(value = "/resetpassword")
    public Result<User> resetPassword(@RequestBody String newpwd,@RequestBody int userid){

        return null;
    }

    @RequestMapping(value = "/checkuserasw")
    public Result<User> checkUserAsw(@RequestBody String account,@RequestBody String question,@RequestBody String asw){
        return null;
    }

    @RequestMapping(value = "/getuserquestion")
    public Result<String> getUserQuestion(@RequestBody String account){

        return null;
    }

    @RequestMapping(value = "/getuserinfo")
    public Result<User> getUserInfo(@RequestBody String account){
        return null;
    }

    @RequestMapping(value = "/do_register")
    public Result<User> doRegister(@RequestBody String account,
                                   @RequestBody String password,
                                   @RequestBody String email,
                                   @RequestBody String phone,
                                   @RequestBody String question,
                                   @RequestBody String asw){
        return null;
    }

    @RequestMapping(value = "/do_login")
    public Result<User> doLogin(@RequestBody String account,@RequestBody String password){
        return null;
    }

    @RequestMapping(value = "/do_check_info")
    public Result<User> doCheckInfo(@RequestBody String info,@RequestBody String type){

        return null;
    }

}
