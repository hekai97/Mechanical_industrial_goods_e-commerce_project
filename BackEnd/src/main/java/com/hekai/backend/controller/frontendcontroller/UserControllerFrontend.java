package com.hekai.backend.controller.frontendcontroller;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.service.UserService;
import com.hekai.backend.utils.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: hekai
 * @Date: 2022/5/29
 * @Status:PASS
 */
@RestController
@RequestMapping(value = "/user")
public class UserControllerFrontend {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserByAccount.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<User> getUserByAccount(String account){
        return userService.findUserByAccount(account);
    }

    @RequestMapping(value = "/do_logout.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<User> doLogOut(HttpSession httpSession){
        httpSession.removeAttribute(ConstUtil.CUR_USER);
        return Result.createRespBySuccess();
    }

    @RequestMapping(value = "/updateuserinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<User> updateUserInfo(HttpSession httpSession,User user){
        User curUser = (User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(curUser == null) {
            return Result.createByErrorMessage("用户尚未登录!");
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

    @RequestMapping(value = "/updatepassword.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updatePassword(HttpSession httpSession,String newpwd,String oldpwd){
        User curUser = (User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(curUser == null) {
            return Result.createByErrorMessage("用户尚未登录!");
        }
        Result<String> result=userService.updatePassword(curUser,newpwd,oldpwd);
        if(result.isSuccess()) {
            httpSession.removeAttribute(ConstUtil.CUR_USER);
        }
        return result;
    }

    @RequestMapping(value = "/resetpassword.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> resetPassword(String newpwd,Integer userid){
        return userService.resetPassword(newpwd,userid);
    }

    @RequestMapping(value = "/checkuserasw.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> checkUserAsw(String account,String question,String asw){
        return userService.checkUserAnswer(account,question,asw);
    }

    @RequestMapping(value = "/getuserquestion.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> getUserQuestion(String account){
        return userService.getUserQuestion(account);
    }

    @RequestMapping(value = "/getuserinfo.do",method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getUserInfo(HttpSession httpSession){
        User curUser = (User) httpSession.getAttribute(ConstUtil.CUR_USER);
        if(curUser == null) {
            System.out.println("此时用户为空"+"sessionId="+httpSession.getId());
            return Result.createByErrorMessage("用户尚未登录!");
        }
        System.out.println("此时用户为"+curUser.getAccount()+"sessionId="+httpSession.getId());
        return userService.findUserByAccount(curUser.getAccount());
    }

    @RequestMapping(value = "/do_register.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<User> doRegister(User user){
        Result<User> result=userService.doRegister(user);
        System.out.println(result.getStatus());
        System.out.println(result.getMsg());
        return result;
    }

    @RequestMapping(value = "/do_login.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<User> doLogin(HttpSession httpSession,String account,String password){
        Result<User> user=userService.doLogin(account,password);
        if(user.isSuccess()){
            httpSession.setAttribute(ConstUtil.CUR_USER,user.getData());
        }
        System.out.println("调用了dologin，account="+account+"密码"+password+"sessionId="+httpSession.getId());
        return user;
    }

    @RequestMapping(value = "/do_check_info.do",method = RequestMethod.POST)
    @ResponseBody
    public Result<User> doCheckInfo(String info,String type){
        return userService.doCheckInfo(info,type);
    }

}
