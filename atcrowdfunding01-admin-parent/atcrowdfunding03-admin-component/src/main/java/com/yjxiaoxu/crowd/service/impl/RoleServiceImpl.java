package com.yjxiaoxu.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxiaoxu.crowd.entity.Role;
import com.yjxiaoxu.crowd.entity.RoleExample;
import com.yjxiaoxu.crowd.mapper.RoleMapper;
import com.yjxiaoxu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:RoleServiceImpl
 * Package:com.yjxiaoxu.crowd.service.impl
 * Description:
 *
 * @Date:2021/9/14 22:02
 * @Author:小许33058485@qq.com
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public PageInfo<Role> selectByRoleName(String roleName, Integer pageNum, Integer pageSize) {
        //  开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectByRoleName(roleName);
        return new PageInfo<>(list);
    }

    @Override
    public boolean saveRole(Role role) {
        boolean flag = true;
        int count = roleMapper.insert(role);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean editRoleByKeyword(Role role) {
        boolean flag = true;
        int count = roleMapper.updateByPrimaryKey(role);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean removeRoleByRoleList(List<Integer> roleList) {
        boolean flag = true;
        // 创建RoleExample对象
        RoleExample roleExample = new RoleExample();
        // 由RoleExample对象创建Criteria对象
        RoleExample.Criteria criteria = roleExample.createCriteria();
        // 封装查询条件
        criteria.andIdIn(roleList);
        int count = roleMapper.deleteByExample(roleExample);
        if (count == 0) {
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Role> getAssignedRoleList(Integer adminId) {

        List<Role> assignedRoleList = roleMapper.getAssignedRoleList(adminId);

        return assignedRoleList;
    }

    @Override
    public List<Role> getUnAssignedRoleList(Integer adminId) {
        List<Role> unAssignedRoleList = roleMapper.getUnAssignedRoleList(adminId);
        return unAssignedRoleList;
    }
}
