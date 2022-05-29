package com.hekai.backend.service;

import com.hekai.backend.entites.reconstruction.Result;
import com.hekai.backend.entites.User;

/**
 * @author: hekai
 * @Date: 2022/5/28
 */
public interface UserService {
    Result<User> updateUser(User user);
}
