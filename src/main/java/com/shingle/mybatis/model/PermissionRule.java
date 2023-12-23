package com.shingle.mybatis.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 * @TableName T_PERMISSION_RULE
 */
@Data
public class PermissionRule implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long permissionObjectId;

    /**
     *
     */
    private String permissionDimension;

    /**
     *
     */
    private String permissionCondition;

    /**
     *
     */
    private String permissionLogic;


    private Integer groupNo;

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