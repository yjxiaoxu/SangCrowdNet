package com.yjxiaoxu.crowd.mvc.controller;

import com.yjxiaoxu.crowd.entity.Auth;
import com.yjxiaoxu.crowd.service.api.AuthService;
import com.yjxiaoxu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * ClassName:AuthController
 * Package:com.yjxiaoxu.crowd.mvc.controller
 * Description:
 *
 * @Date:2021/10/8 16:32
 * @Author:小许33058485@qq.com
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    // 获取所有的权限并以集合的方式返回
    @RequestMapping("/assign/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth() {

        // 获取所有的权限信息
        List<Auth> authList = authService.getAllAuth();

        return ResultEntity.successWithDate(authList);
    }

    // 根据角色id获取其所拥有的权限，以集合的方式返回
    @RequestMapping("auth/get/auth/by/id.json")
    public ResultEntity<List<Integer>> getAuthIdListByRoleId(@RequestParam("id") Integer roleId) {

        List<Integer> authIdList = authService.getAuthIdListByRoleId(roleId);
        return ResultEntity.successWithDate(authIdList);
    }

    // 通过角色id给角色分配指定的权限
    @RequestMapping("/auth/assign/auth/to/role.json")
    public ResultEntity<String> assignAuthToRole(@RequestBody Map<String, List<Integer>> map) {
        authService.assignAuthToRole(map);
        return ResultEntity.successWithOutDate();
    }
}
