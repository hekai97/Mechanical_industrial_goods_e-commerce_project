package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyUser;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    private UserService userServiceImp;


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
    public Result<List<SimplifyUser>> findUserList(){
        return userServiceImp.findUserList();
    }

    @RequestMapping(value = "/login")
    public Result<User> loginAdmin(@RequestBody String account,@RequestBody String password){
        return userServiceImp.getLoginAdmin(account,password);
    }


    //前台部分

}
