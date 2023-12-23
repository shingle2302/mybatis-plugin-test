package com.shingle.mybatis.model;

import lombok.Data;

import java.util.Date;


@Data
public class User {

    private Long id;
    private String username;

    private String password;


    private String email;


    private Date createdAt;

    private Date updatedAt;
}
