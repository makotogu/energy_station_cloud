package com.gujiacheng.dataService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gujiacheng.dataService.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名和密码实现用户查询
     * @param username 用户名
     * @param password 密码
     * @return 返回用户entity
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword (String username, String password);
}
