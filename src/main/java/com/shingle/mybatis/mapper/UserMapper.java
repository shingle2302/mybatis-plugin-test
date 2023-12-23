package com.shingle.mybatis.mapper;

import com.shingle.mybatis.command.UserQuery;
import com.shingle.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 */
@Mapper
public interface UserMapper {
    List<User> queryByPage(UserQuery user);

}
