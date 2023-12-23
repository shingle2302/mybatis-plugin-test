package com.shingle.mybatis.enums;

import lombok.Getter;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 */

@Getter
public enum SqlCondition {
    EQ("=", "等于"),
    NE("!=", "不等于"),
    GT(">", "大于"),
    GE(">=", "大于等于"),
    LT("<", "小于"),
    LE("<=", "小于等于"),
    LIKE("LIKE", "包含"),
    NOT_LIKE("NOT_LIKE", "不包含"),
    IN("IN", "在集合中"),
    NOT_IN("NOT_IN", "不在集合中"),
    IS_NULL("IS_NULL", "为空"),
    IS_NOT_NULL("IS_NOT_NULL", "不为空"),
    ;


    private final String code;
    private final String description;

    SqlCondition(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static SqlCondition getConditionByCode(String code) {
        for (SqlCondition condition : SqlCondition.values()) {
            if (condition.code.equalsIgnoreCase(code)) {
                return condition;
            }
        }
        return null;
    }
}
