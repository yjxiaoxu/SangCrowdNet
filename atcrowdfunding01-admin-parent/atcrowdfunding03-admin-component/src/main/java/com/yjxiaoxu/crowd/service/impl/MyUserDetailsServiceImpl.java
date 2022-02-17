package com.yjxiaoxu.crowd.service.impl;

import com.yjxiaoxu.crowd.entity.Admin;
import com.yjxiaoxu.crowd.entity.Role;
import com.yjxiaoxu.crowd.mvc.vo.UserVO;
import com.yjxiaoxu.crowd.service.api.AdminService;
import com.yjxiaoxu.crowd.service.api.AuthService;
import com.yjxiaoxu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:MyUserDetailsServiceImpl
 * Package:com.yjxiaoxu.crowd.service.impl
 * Description:定义一个类实现UserDetailsService
 *
 * @Date:2021/10/19 16:50
 * @Author:小许33058485@qq.com
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据username获取Admin对象
        Admin admin = adminService.getAdminByUsername(username);
        Integer adminId = admin.getId();

        // 根据admin的Id获取其已分配的角色和权限
        List<Role> roleList = roleService.getAssignedRoleList(adminId);
        List<String> authNameList = authService.getAssignedAuthNameListByAdminId(adminId);

        // 创建集合用来存储GrantedAuthority集合
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role: roleList) {

            // Spring Security要求角色的前面要添加ROLE_
            String roleName = "ROLE_" + role.getName();

            // 创建GrantAuthority对象，用来保存admin已分配的角色
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(grantedAuthority);
        }

        for (String authName: authNameList) {
            // 创建GrantAuthority对象，用来保存admin已分配的权限
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(grantedAuthority);
        }

        // 将admin和GrantAuthority集合封装成UserVO对象
        UserVO userVO = new UserVO(admin, authorities);
        return userVO;
    }
}
