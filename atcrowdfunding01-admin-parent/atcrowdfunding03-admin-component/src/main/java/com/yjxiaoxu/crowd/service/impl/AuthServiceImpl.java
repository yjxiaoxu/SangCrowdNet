package com.yjxiaoxu.crowd.service.impl;

import com.yjxiaoxu.crowd.entity.Auth;
import com.yjxiaoxu.crowd.entity.AuthExample;
import com.yjxiaoxu.crowd.mapper.AuthMapper;
import com.yjxiaoxu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:AuthServiceImpl
 * Package:com.yjxiaoxu.crowd.service.impl
 * Description:
 *
 * @Date:2021/10/8 16:29
 * @Author:小许33058485@qq.com
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuth() {
        List<Auth> authList = authMapper.selectByExample(new AuthExample());
        return authList;
    }

    @Override
    public List<Integer> getAuthIdListByRoleId(Integer roleId) {

        List<Integer> authIdList = authMapper.getAuthIdListByRoleId(roleId);
        return authIdList;
    }

    @Override
    public void assignAuthToRole(Map<String, List<Integer>> map) {

        // 获取要分配权限的角色id集合
        List<Integer> roleIdArray = map.get("roleIdArray");

        // 因为每次只给一个角色分配权限
        Integer roleId = roleIdArray.get(0);
        List<Integer> authIdList = map.get("authIdArray");

        // 在存储角色与权限之间的关系前先删除当前角色的所有权限
        authMapper.purgeRoleAndAuthRelationship(roleId);

        // 在执行分配角色操作前要先判断用户是否给当前用户分配了角色
        if (authIdList != null && authIdList.size() > 0) {

            authMapper.saveRoleAndAuthRelationship(roleId, authIdList);
        }
    }

    @Override
    public List<String> getAssignedAuthNameListByAdminId(Integer adminId) {
        return authMapper.getAssignedAuthNameListByAdminId(adminId);
    }
}
