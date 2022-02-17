package com.yjxiaoxu.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.yjxiaoxu.crowd.entity.Role;
import com.yjxiaoxu.crowd.service.api.RoleService;
import com.yjxiaoxu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName:RoleController
 * Package:com.yjxiaoxu.crowd.mvc.controller
 * Description:
 *
 * @Date:2021/9/14 22:02
 * @Author:小许33058485@qq.com
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    // 根据role名称模糊查询role集合
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>>  getRoleListByRoleName(
            @RequestParam(value="roleName", defaultValue="") String roleName,
            @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
            @RequestParam(value="pageSize", defaultValue="5") Integer pageSize) {
        PageInfo<Role> pageInfo = roleService.selectByRoleName(roleName, pageNum, pageSize);
        //System.out.println("pageInfo的list的长度为=-===========" + pageInfo.getList().size());
        return ResultEntity.successWithDate(pageInfo);
    }
    // 保存角色名称的方法
    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> saveRole(Role role) {
        roleService.saveRole(role);
        return ResultEntity.successWithOutDate();
    }
    // 修改角色名称的方法
    @ResponseBody
    @RequestMapping("/role/edit.json")
    public ResultEntity<String> editRoleByKeyword(Role role) {
        roleService.editRoleByKeyword(role);
        return ResultEntity.successWithOutDate();
    }
    // 根基角色id单条或批量删除角色信息
    @ResponseBody
    @RequestMapping("/role/remove/byId/list.json")
    public ResultEntity<String> removeRoleByIdList(@RequestBody List<Integer> roleList) {
        roleService.removeRoleByRoleList(roleList);
        return ResultEntity.successWithOutDate();
    }
}
