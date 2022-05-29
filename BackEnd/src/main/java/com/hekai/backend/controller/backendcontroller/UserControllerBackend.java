package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.reconstruction.Result;
import com.hekai.backend.entites.User;
import com.hekai.backend.entites.reconstruction.SimplifyUser;
import com.hekai.backend.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping("/mgr/user")
public class UserControllerBackend {
    //后台部分
    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping(value = "/updateuser")
    public Result<User> updateUser(@RequestBody User user){
        return userServiceImp.updateUser(user);
    }

    @RequestMapping(value = "/finduser")
    public Result<SimplifyUser> findUser(@RequestBody Integer id){
        return userServiceImp.findUserById(id);
    }

    @RequestMapping(value = "/deleteusers")
    public Result<User> deleteUsers(@RequestBody Integer id){
        return userServiceImp.deleteUserById(id);
    }

    @RequestMapping(value = "/finduserlist")
    public Result<List<User>> findUserList(){
        return userServiceImp.findUserList();
    }

    @RequestMapping(value = "/login")
    public Result<User> loginAdmin(){
        return null;
    }


    //前台部分

}
