package com.shingle.mybatis.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shingle.mybatis.mapper.UserMapper;
import com.shingle.mybatis.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shing2302
 */
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/queryByPage")
    List<User> queryByPage() {
        Page<User> page = PageHelper.startPage(1, 10);
        User user = new User();
//        user.setUsername("user");
        userMapper.queryByPage(user);
        return page;
    }
}
