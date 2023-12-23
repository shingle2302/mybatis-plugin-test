package com.shingle.mybatis.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 * @TableName T_PERMISSION_OBJECT
 */
@Data
public class PermissionObject implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String permissionCode;

    /**
     *
     */
    private String permissionName;

    /**
     *
     */
    private Date createdAt;

    /**
     *
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;


}