package com.yjxiaoxu.crowd.mapper;

import com.yjxiaoxu.crowd.entity.Role;
import com.yjxiaoxu.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    // 根据role名称模拟查询Role集合
    List<Role> selectByRoleName(String roleName);

    // 获取admin已分配的角色集合
    List<Role> getAssignedRoleList(Integer adminId);

    // 获取admin未分配的角色集合
    List<Role> getUnAssignedRoleList(Integer adminId);
}