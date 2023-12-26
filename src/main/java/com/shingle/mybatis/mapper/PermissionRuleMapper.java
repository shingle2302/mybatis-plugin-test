package com.shingle.mybatis.mapper;

import com.shingle.mybatis.model.PermissionRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shingle2302
 * @email shingle2302@qq.com
 * @description 针对表【T_PERMISSION_RULE】的数据库操作Mapper
 * @createDate 2023-12-23 19:28:46
 * @Entity com.shingle.mybatis.model.PermissionRule
 */
@Mapper
public interface PermissionRuleMapper {

    List<PermissionRule> queryByPermissionCode(String permissionCode);
}




