package com.yjxiaoxu.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.yjxiaoxu.crowd.entity.Admin;
import com.yjxiaoxu.crowd.service.api.AdminService;
import com.yjxiaoxu.crowd.util.CrowdUtil;
import com.yjxiaoxu.crowd.util.SysTimeUtil;
import com.yjxiaoxu.crowd.util.constant.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName:AdminController
 * Package:com.yjxiaoxu.crowd.mvc.controller
 * Description:
 *
 * @Date:2021/8/15 14:37
 * @Author:小许33058485@qq.com
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap) {
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList", adminList);
        return "target";
    }
    //登录方法
    @RequestMapping("/admin/do/login.html")
    public String toLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session) {

        //根据账号密码获取Admin对象
        Admin admin = adminService.getAdminByAccount(username, password);
        //如果Admin不为空，则说明账号密码正确，将Admin放到Session中
        session.setAttribute(ConstantUtil.ATTR_NAME_ADMIN, admin);
        // 为了避免跳转到后台主页面再刷新浏览器导致重复提交登录表单，重定向到目
        //标页面
        return "redirect:/admin/to/main/page.html";
    }
    @RequestMapping("/admin/do/logout.html")
    // 退出登录方法
    public String loginOut(HttpSession session) {
        // 强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }
    // 跳转到用户管理页面方法
    @RequestMapping("/admin/get/page.html")
    public String getPage(@RequestParam(value = "keyWord", defaultValue = "") String keyWord,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          ModelMap modelMap
                          ) {
        PageInfo<Admin> pageInfo = adminService.selectByKeyWord(keyWord, pageNum, pageSize);
        modelMap.addAttribute(ConstantUtil.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }
    // 根基Admin的id删除Admin
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyWord}.html")
    public String removeAdmin(@PathVariable("adminId") Integer adminId,
                              @PathVariable("keyWord") String keyWord,
                              @PathVariable("pageNum") Integer pageNum,
                              Model model) {
        adminService.deleteByPrimaryKey(adminId);

        // return "redirect:/×××/××/?keyWord=中文     在浏览器解析时会出现乱码  /×××/××/?keyWord=??
        // spring mvc 有自定义的org.springframework.ui.Model类用与封装url携带的参数
        // 将方法参数添加到spring自定义的对像中这样就不会乱码的情况
        model.addAttribute("keyWord", keyWord);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum;
    }
    //添加admin的方法
    @RequestMapping("/admin/save.html")
    public String insertAdmin(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }
    // 根据id获取admin的方法
    @RequestMapping("/admin/to/edit.html")
    public String toEdit(@RequestParam("adminId") Integer adminId,
                         ModelMap modelMap) {
        Admin admin = adminService.getAdminByAdminId(adminId);
        modelMap.addAttribute(ConstantUtil.ATTR_NAME_ADMIN, admin);
        return "admin-edit";
    }
    // 根据id修改Admin的方法
    @RequestMapping("/admin/edit.html")
    public String editAdmin(Admin admin,
                            @RequestParam("keyWord") String keyWord,
                            @RequestParam("pageNum") Integer pageNum,
                            Model model) {
        System.out.println(admin);
        adminService.editAdminByAdminId(admin);
        // return "redirect:/×××/××/?keyWord=中文     在浏览器解析时会出现乱码  /×××/××/?keyWord=??
        // spring mvc 有自定义的org.springframework.ui.Model类用与封装url携带的参数
        // 将方法参数添加到spring自定义的对像中这样就不会乱码的情况
        model.addAttribute("keyWord", keyWord);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum;
    }
}
