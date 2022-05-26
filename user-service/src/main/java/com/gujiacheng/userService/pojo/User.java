package com.gujiacheng.userService.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private String id;
    private String password;
}
