package com.hekai.backend.service;

import com.hekai.backend.entites.reconstruction.Result;
import com.hekai.backend.entites.User;
import com.hekai.backend.entites.reconstruction.SimplifyUser;

import java.util.List;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface UserService {
    Result<User> updateUser(User user);

    Result<SimplifyUser> findUserById(Integer id);

    Result<User> deleteUserById(Integer id);

    Result<List<User>> findUserList();
}
