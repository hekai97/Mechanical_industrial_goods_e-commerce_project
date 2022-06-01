package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.reConstruction.compositeEntities.StatusCode;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyUser;
import com.hekai.backend.repository.UserRepository;
import com.hekai.backend.service.UserService;
import com.hekai.backend.utils.ConstUtil;
import com.hekai.backend.utils.MD5Util;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class UserServiceImp implements UserService {
    private static final int NormalRole=1;
    private static final int AdminRole=2;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Result<User> updateUser(User user) {
        User foundUser= userRepository.findUsersById(user.getId());
        foundUser.setName(user.getName());
        foundUser.setAccount(user.getAccount());
        foundUser.setAge(user.getAge());
        foundUser.setPhone(user.getPhone());
        foundUser.setEmail(user.getEmail());
        foundUser.setSex(user.getSex());
        User user1=userRepository.save(foundUser);
        if(user.getId()==user1.getId()){
            return Result.createRespBySuccess("用户信息修改成功",user1);
        }
        return Result.createByErrorMessage("用户信息修改失败");
    }

    @Override
    public Result<SimplifyUser> findUserById(Integer id) {
        User user=userRepository.findUsersById(id);
        Result<SimplifyUser> result=new Result<>();
        if(user==null){
            result.setStatus(Result.Error);
            result.setData(null);
            result.setMsg("获取用户数据失败！");
        }else{
            result.setStatus(Result.Normal);
            result.setData(new SimplifyUser().ConvertFromUser(user));
            result.setMsg("获取成功");
        }
        return result;
    }

    @Override
    public Result<User> deleteUserById(Integer id) {
        userRepository.deleteById(id);
        Result<User> result=new Result<>();
        result.setStatus(Result.Normal);
        return result;
    }

    @Override
    public Result<List<SimplifyUser>> findUserList() {
        List<User> allUsers=userRepository.findAll();
        List<SimplifyUser> allSimplifyUser=new ArrayList<>();
        for (User u:allUsers) {
            allSimplifyUser.add(new SimplifyUser().ConvertFromUser(u));
        }
        Result<List<SimplifyUser>> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setData(allSimplifyUser);
        return result;
    }

    @Override
    public Result<User> getLoginAdmin(String account, String password) {
        User user= userRepository.findUsersByRoleAndAccount(AdminRole,account);
        Result<User> result=new Result<>();
        if(user!=null){
            if(password.equals(user.getPassword())){
                result.setStatus(Result.Normal);
                user.setPassword("");
                result.setData(user);
                result.setMsg("登录成功！");
            }
            else{
                result.setStatus(Result.Error);
                result.setMsg("密码错误！");
            }
        }else{
            result.setStatus(Result.Error);
            result.setMsg("不是管理员，无法登录！");
        }
        return result;
    }

    @Override
    public Result<User> doLogin(String account, String password) {
        User user=userRepository.findUserByAccount(account);
        if(user==null){
            return Result.createByErrorMessage("用户不存在！");
        }
        String md5Password= MD5Util.MD5Encode(password,"utf-8",false);
        if(!md5Password.equals(user.getPassword())){
            return Result.createByErrorMessage("密码错误！");
        }
        user.setPassword("");
        return Result.createRespBySuccess("登录成功",user);
    }

    @Override
    public Result<User> doRegister(User user) {
        if((userRepository.findUserByAccount(user.getAccount()))!=null){
            return Result.createByErrorMessage("用户已存在！");
        }
        if(userRepository.findUserByEmail(user.getEmail())!=null){
            return Result.createByErrorMessage("该邮箱已注册");
        }
        user.setRole(ConstUtil.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Util.MD5Encode(user.getPassword(),"utf-8",false));
        Date currentDate=new Date();
        user.setCreateTime(new Timestamp(currentDate.getTime()));
        user.setUpdateTime(new Timestamp(currentDate.getTime()));
        userRepository.save(user);
        return Result.createRespBySuccessMessage("注册成功");
    }

    @Override
    public Result<User> findUserByAccount(String account) {
        User user=userRepository.findUserByAccount(account);
        if(user==null){
            return Result.createByErrorMessage("用户不存在！");
        }
        user.setPassword("");
        user.setAsw("");
        return Result.createRespBySuccess(user);
    }

    @Override
    public Result<String> getUserQuestion(String account) {
        User user=userRepository.findUserByAccount(account);
        if(user==null){
            return Result.createByErrorMessage("用户名不存在！");
        }
        if(user.getQuestion()!=null&&!user.getQuestion().isEmpty()){
            return Result.createRespBySuccess(user.getQuestion());
        }
        return Result.createByErrorMessage("未设置密保问题！");
    }

    @Override
    public Result<String> checkUserAnswer(String account, String question, String asw) {
        User user=userRepository.findUserByAccount(account);
        if(user!=null){
            if(user.getQuestion().equals(question)){
                if(user.getAsw().equals(asw)){
                    String token= UUID.randomUUID().toString();
                    return Result.createRespBySuccessMessage(token);
                }
                return Result.createByErrorMessage("密保答案错误");
            }
            return Result.createByErrorMessage("密保问题错误");
        }
        return Result.createByErrorMessage("无此用户");
    }

    @Override
    public Result<String> resetPassword(String newpwd, Integer userid) {
        User user= userRepository.findUsersById(userid);
        user.setPassword(MD5Util.MD5Encode(newpwd,"utf-8",false));
        user.setUpdateTime(new Timestamp(new Date().getTime()));
        if(userRepository.save(user).getId()==userid){
            return Result.createRespBySuccessMessage("修改成功");
        }
        return Result.createByErrorMessage("修改失败");
    }

    @Override
    public Result<String> updatePassword(User curUser, String newpwd, String oldpwd) {
        User findUser=userRepository.findUserByAccount(curUser.getAccount());
        String oldMd5Password=MD5Util.MD5Encode(oldpwd,"utf-8",false);
        if(!oldMd5Password.equals(findUser.getPassword())){
            return Result.createByErrorMessage("旧密码错误");
        }
        findUser.setPassword(MD5Util.MD5Encode(newpwd,"utf-8",false));
        findUser.setUpdateTime(new Timestamp(new Date().getTime()));
        if(userRepository.save(findUser).getId()== curUser.getId()){
            return Result.createRespBySuccess("密码修改成功");
        }
        return Result.createByErrorMessage("密码修改失败");
    }

    @Override
    public Result<User> doCheckInfo(String info, String type) {
        if (ConstUtil.TYPE_ACCOUNT.equals(type)) {
            User user = userRepository.findUserByAccount(info);
            if (user!=null) {
                return Result.createByErrorMessage("用户名已存在!");
            }
        }
        if (ConstUtil.TYPE_EMAIL.equals(type)) {
            User user = userRepository.findUserByEmail(info);
            if(user!=null) {
                return Result.createByErrorMessage("邮箱已被注册!");
            }
        }
        if (ConstUtil.TYPE_PHONE.equals(type)) {
            User user = userRepository.findUserByPhone(info);
            if(user!=null) {
                return Result.createByErrorMessage("电话号码已被注册!");
            }
        }
        return Result.createRespBySuccessMessage("信息验证成功!");
    }
}
