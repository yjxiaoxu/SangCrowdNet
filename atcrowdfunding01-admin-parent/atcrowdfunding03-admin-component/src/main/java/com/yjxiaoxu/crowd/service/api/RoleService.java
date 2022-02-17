package com.yjxiaoxu.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.yjxiaoxu.crowd.entity.Role;

import java.util.List;

/**
 * ClassName:RoleService
 * Package:com.yjxiaoxu.crowd.service.api
 * Description:
 *
 * @Date:2021/9/14 22:02
 * @Author:小许33058485@qq.com
 */
public interface RoleService {

    // 根据role名称模拟查询role集合
    PageInfo<Role> selectByRoleName(String roleName, Integer pageNum, Integer pageSize);

    // 添加角色方法
    boolean saveRole(Role role);

    // 根据角色id修改角色名称
    boolean editRoleByKeyword(Role role);

    // 根据角色集合删除角色信息(可以单条、批量删除)
    boolean removeRoleByRoleList(List<Integer> roleList);

    // 获取admin已分配的角色
    List<Role> getAssignedRoleList(Integer adminId);

    // 获取admin未分配的角色
    List<Role> getUnAssignedRoleList(Integer adminId);
}
