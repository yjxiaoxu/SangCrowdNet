package com.yjxiaoxu.crowd.service.api;

import com.yjxiaoxu.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * ClassName:AuthService
 * Package:com.yjxiaoxu.crowd.service.api
 * Description:
 *
 * @Date:2021/10/8 16:29
 * @Author:小许33058485@qq.com
 */
public interface AuthService {

    // 获取所有的权限信息
    List<Auth> getAllAuth();

    List<Integer> getAuthIdListByRoleId(Integer roleId);

    void assignAuthToRole(Map<String, List<Integer>> map);

    // 根据AdminId获取Admin已分配的权限的name
    List<String> getAssignedAuthNameListByAdminId(Integer adminId);
}
