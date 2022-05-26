package com.gujiacheng.userService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gujiacheng.userService.mapper.UserMapper;
import com.gujiacheng.userService.pojo.User;
import com.gujiacheng.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User userCheck(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public User userRegister(String username, String password) {
        User newUser = new User();
        newUser.setId(username);
        newUser.setPassword(password);
        userMapper.insert(newUser);
        return newUser;
    }
}
