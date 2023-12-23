package com.shingle.mybatis.mapper;

import com.shingle.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shing2302
 */
@Mapper
public interface UserMapper {
    List<User> queryByPage(User user);

}
