package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.repository.UserRepository;
import com.hekai.backend.service.UserService;
import com.hekai.backend.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author: hekai
 * @Date: 2022/5/29
 */
@RestController
@RequestMapping(value = "/user")
public class UserControllerFrontend {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserByAccount")
    public Result<User> getUserByAccount(String account){
        return userService.findUserByAccount(account);
    }

    @RequestMapping(value = "/do_logout")
    public Result<User> doLogOut(HttpSession httpSession){
        httpSession.removeAttribute(ConstUtil.CUR_USER);
        return Result.createRespBySuccess();
    }

    @RequestMapping(value = "/updateuserinfo")
    public Result<User> updateUserInfo(HttpSession httpSession,@RequestBody User user){
        User curUser = (User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(curUser == null) {
            return Result.createByErrorMessage("用户尚未登陆!");
        }
        user.setId(curUser.getId());
        user.setAccount(curUser.getAccount());
        Result<User> result=userService.updateUser(user);
        if (result.isSuccess()) {
            //重写session
            httpSession.setAttribute(ConstUtil.CUR_USER, result.getData());
        }
        return result;
    }

    @RequestMapping(value = "/updatepassword")
    public Result<String> updatePassword(HttpSession httpSession,String newpwd,String oldpwd){
        User curUser = (User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(curUser == null) {
            return Result.createByErrorMessage("用户尚未登陆!");
        }
        Result<String> result=userService.updatePassword(curUser,newpwd,oldpwd);
        if(result.isSuccess()) {
            httpSession.removeAttribute(ConstUtil.CUR_USER);
        }
        return result;
    }

    @RequestMapping(value = "/resetpassword")
    public Result<String> resetPassword(String newpwd,Integer userid){
        return userService.resetPassword(newpwd,userid);
    }

    @RequestMapping(value = "/checkuserasw")
    public Result<String> checkUserAsw(String account,String question,String asw){
        return userService.checkUserAnswer(account,question,asw);
    }

    @RequestMapping(value = "/getuserquestion")
    public Result<String> getUserQuestion(String account){
        return userService.getUserQuestion(account);
    }

    @RequestMapping(value = "/getuserinfo")
    public Result<User> getUserInfo(HttpSession httpSession){
        User curUser = (User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(curUser == null) {
            return Result.createByErrorMessage("用户尚未登陆!");
        }
        return userService.findUserByAccount(curUser.getAccount());
    }

    @RequestMapping(value = "/do_register")
    public Result<User> doRegister(User user){
        return userService.doRegister(user);
    }

    @RequestMapping(value = "/do_login")
    public Result<User> doLogin(HttpSession httpSession,@RequestBody String account,@RequestBody String password){
        Result<User> user=userService.doLogin(account,password);
        if(user.isSuccess()){
            httpSession.setAttribute(ConstUtil.CUR_USER,user);
        }
        return user;
    }

    @RequestMapping(value = "/do_check_info")
    public Result<User> doCheckInfo(String info,String type){
        return userService.doCheckInfo(info,type);
    }

}
