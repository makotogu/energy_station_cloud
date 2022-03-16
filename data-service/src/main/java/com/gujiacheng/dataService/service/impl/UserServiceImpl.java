package com.gujiacheng.dataService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gujiacheng.dataService.mapper.UserMapper;
import com.gujiacheng.dataService.pojo.User;
import com.gujiacheng.dataService.service.UserService;
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
}
