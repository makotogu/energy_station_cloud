package com.gujiacheng.userService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gujiacheng.userService.controller.utils.R;
import com.gujiacheng.userService.pojo.User;
import com.gujiacheng.userService.service.UserService;
import com.gujiacheng.userService.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public R login(@RequestBody User user) throws JsonProcessingException {
        User checkedUser;
        checkedUser = userService.userCheck(user.getId(), user.getPassword());
        if (checkedUser == null) {
            return new R(false);
        } else {
            Map<String, String> payload = JWTUtils.setupPayload(checkedUser.getId());
            String token = JWTUtils.getToken(payload);
            HashMap<String, String> map = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(checkedUser);
            map.put("token", token);
            map.put("userInfo", json);
            return new R(true, map);
        }
    }

    @PostMapping("/register")
    public R register(@RequestBody User user) {
        try {
            User newUser = userService.userRegister(user.getId(), user.getPassword());
            System.out.println(newUser);
            return login(newUser);
        } catch (Exception e) {
            return new R(false);
        }
    }

    @GetMapping("/auth")
    public R checkAuth() {
        return new R(true);
    }
}
