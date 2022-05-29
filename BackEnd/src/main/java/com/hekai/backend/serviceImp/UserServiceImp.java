package com.hekai.backend.serviceImp;

import com.hekai.backend.entites.reconstruction.Result;
import com.hekai.backend.entites.User;
import com.hekai.backend.repository.UserRepository;
import com.hekai.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
@Service
public class UserServiceImp implements UserService {
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
        result.setStatus(0);
        result.setMessage("用户信息修改成功");
        result.setData(userRepository.findUsersById(user.getId()));
        return result;
    }
}
