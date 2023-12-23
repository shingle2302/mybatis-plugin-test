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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PermissionObject other = (PermissionObject) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPermissionCode() == null ? other.getPermissionCode() == null : this.getPermissionCode().equals(other.getPermissionCode()))
                && (this.getPermissionName() == null ? other.getPermissionName() == null : this.getPermissionName().equals(other.getPermissionName()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPermissionCode() == null) ? 0 : getPermissionCode().hashCode());
        result = prime * result + ((getPermissionName() == null) ? 0 : getPermissionName().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", permissionCode=" + permissionCode +
                ", permissionName=" + permissionName +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}