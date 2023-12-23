package com.shingle.mybatis.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends PermissionQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String email;
}
