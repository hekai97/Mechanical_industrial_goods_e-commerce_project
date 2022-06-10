package com.hekai.backend.controller.backendcontroller;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyUser;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.service.UserService;
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
@RequestMapping("/mgr/user")
public class UserControllerBackend {
    //后台部分
    @Autowired
    private UserService userServiceImp;


    @RequestMapping(value = "/updateuser.do")
    public Result<User> updateUser(HttpSession httpSession, @RequestBody User user){
        User currentUser=(User)httpSession.getAttribute("CurrentUser");
        return userServiceImp.updateUser(user);
    }

    @RequestMapping(value = "/finduser.do")
    public Result<SimplifyUser> findUser(HttpSession httpSession,@RequestBody Integer id){
        return userServiceImp.findUserById(id);
    }

    @RequestMapping(value = "/deleteusers.do")
    public Result<User> deleteUsers(HttpSession httpSession,@RequestBody Integer id){
        return userServiceImp.deleteUserById(id);
    }

    @RequestMapping(value = "/finduserlist.do")
    public Result<List<SimplifyUser>> findUserList(HttpSession httpSession){
        return userServiceImp.findUserList();
    }

    @RequestMapping(value = "/login.do")
    public Result<User> loginAdmin(HttpSession httpSession,@RequestBody String account,@RequestBody String password){
        return userServiceImp.getLoginAdmin(account,password);
    }


    //前台部分

}
