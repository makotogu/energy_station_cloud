package com.gujiacheng.userService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gujiacheng.userService.pojo.User;

public interface UserService extends IService<User> {

    /**
     * 检查用户名和密码的合法性
     * @param username 用户名
     * @param password 密码
     * @return 返回检查结果
     */
    User userCheck(String username, String password);

    User userRegister(String username, String password);
}
