package com.gujiacheng.userService.controller;

import com.gujiacheng.userService.controller.utils.R;
import com.gujiacheng.userService.pojo.User;
import com.gujiacheng.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录检查
     * @param user 用户信息的对象
     * @return 检查结果
     */
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        User checkedUser = new User();
        checkedUser = userService.userCheck(user.getUsername(), user.getPassword());
        if (checkedUser == null) {
            return new R(false);
        } else {
            return new R(true, checkedUser);
        }
    }

    @PostMapping("/register")
    public R register(@RequestBody User user) {
        try {
            userService.save(user);
            return new R(true);
        } catch (Exception e) {
            return new R(false);
        }
    }

    @GetMapping("/listAll")
    public R getUserList() {
        return new R(true,userService.list());
    }
}
