package com.shingle.mybatis.model;

import lombok.Data;

import java.util.Date;


/**
 * @author shingle2302
 * @email shingle2302@qq.com
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Date createdAt;

    private Date updatedAt;
}
