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
        PermissionRule other = (PermissionRule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPermissionObjectId() == null ? other.getPermissionObjectId() == null : this.getPermissionObjectId().equals(other.getPermissionObjectId()))
                && (this.getPermissionDimension() == null ? other.getPermissionDimension() == null : this.getPermissionDimension().equals(other.getPermissionDimension()))
                && (this.getPermissionLogic() == null ? other.getPermissionLogic() == null : this.getPermissionLogic().equals(other.getPermissionLogic()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPermissionObjectId() == null) ? 0 : getPermissionObjectId().hashCode());
        result = prime * result + ((getPermissionDimension() == null) ? 0 : getPermissionDimension().hashCode());
        result = prime * result + ((getPermissionLogic() == null) ? 0 : getPermissionLogic().hashCode());
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
                ", permissionObjectId=" + permissionObjectId +
                ", permissionDimension=" + permissionDimension +
                ", permissionLogic=" + permissionLogic +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
        return sb;
    }
}