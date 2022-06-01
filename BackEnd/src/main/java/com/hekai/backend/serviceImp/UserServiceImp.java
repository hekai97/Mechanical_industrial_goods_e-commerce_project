package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyUser;
import com.hekai.backend.repository.UserRepository;
import com.hekai.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        userRepository.save(foundUser);
        Result<User> result=new Result<>();
        result.setStatus(Result.Normal);
        result.setMsg("用户信息修改成功！");
        result.setData(userRepository.findUsersById(user.getId()));
        return result;
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
}
