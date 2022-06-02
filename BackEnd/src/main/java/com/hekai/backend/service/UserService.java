package com.hekai.backend.service;

import com.hekai.backend.entites.reConstruction.compositeEntities.Result;
import com.hekai.backend.entites.sourceEntites.User;
import com.hekai.backend.entites.reConstruction.singleEntites.SimplifyUser;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface UserService {
    Result<User> updateUser(User user);

    Result<SimplifyUser> findUserById(Integer id);

    Result<User> deleteUserById(Integer id);

    Result<List<SimplifyUser>> findUserList();

    Result<User> getLoginAdmin(String account, String password);

    Result<User> doLogin(String account, String password);

    Result<User> doRegister(User user);

    Result<User> findUserByAccount(String account);

    Result<String> getUserQuestion(String account);

    Result<String> checkUserAnswer(String account, String question, String asw);

    Result<String> resetPassword(String newpwd, Integer userid);

    Result<String> updatePassword(User curUser, String newpwd, String oldpwd);

    Result<User> doCheckInfo(String info, String type);
}
