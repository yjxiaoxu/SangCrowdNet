package com.yjxiaoxu.crowd.mvc.controller;

import com.yjxiaoxu.crowd.entity.Role;
import com.yjxiaoxu.crowd.service.api.AdminService;
import com.yjxiaoxu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName:AssignController
 * Package:com.yjxiaoxu.crowd.mvc.controller
 * Description:
 *
 * @Date:2021/9/26 21:15
 * @Author:小许33058485@qq.com
 */
@Controller
public class AssignController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;

    // 给Admin分配角色的方法
    @RequestMapping("/assign/do/admin/assign/role/page.html")
    public String assignRoleToAdmin(@RequestParam("adminId") Integer adminId,
                                    @RequestParam("pageNum") Integer pageNum,
                                    @RequestParam("keyWord") String keyWord,
                                    @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList,
                                    Model model
                                    ) {

        // 根据用户给当前Admin分配的角色数据进行重新分配操作
        adminService.saveAdminAndRoleRelationship(adminId, roleIdList);

        // return "redirect:/×××/××/?keyWord=中文     在浏览器解析时会出现乱码  /×××/××/?keyWord=??
        // spring mvc 有自定义的org.springframework.ui.Model类用与封装url携带的参数
        // 将方法参数添加到spring自定义的对像中这样就不会乱码的情况

        model.addAttribute("keyWord", keyWord);

        return "redirect:/admin/get/page.html?pageNum=" + pageNum;
    }

    @RequestMapping("/assign/to/role/page.html")
    // 跳转到给Admin分配角色的页面的方法
    public String ToAssignRoleToAdmin(@RequestParam("adminId") Integer adminId,
                                    ModelMap modelMap) {

        // 获取当前操作的Admin已分配的角色集合
        List<Role> assignedRoleList = roleService.getAssignedRoleList(adminId);

        // 获取当前操作的Admin未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRoleList(adminId);

        modelMap.addAttribute("assignedRoleList", assignedRoleList);

        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);

        return "assign-role";

    }
}
