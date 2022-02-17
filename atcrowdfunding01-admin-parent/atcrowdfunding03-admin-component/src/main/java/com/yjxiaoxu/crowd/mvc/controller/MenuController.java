package com.yjxiaoxu.crowd.mvc.controller;

import com.yjxiaoxu.crowd.entity.Menu;
import com.yjxiaoxu.crowd.service.api.MenuService;
import com.yjxiaoxu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:MenuController
 * Package:com.yjxiaoxu.crowd.mvc.controller
 * Description:
 *
 * @Date:2021/9/23 20:27
 * @Author:小许33058485@qq.com
 */
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    // 删除Menu的方法
    @RequestMapping("/menu/remove.json")
    public ResultEntity<String> removeMenu(@RequestParam("id") Integer id) {
        menuService.removeMenu(id);
        return ResultEntity.successWithOutDate();
    }

    // 修改Menu的方法
    @RequestMapping("/menu/edit.json")
    public ResultEntity<String> editMenu(Menu menu) {
        menuService.editMenu(menu);
        return ResultEntity.successWithOutDate();
    }

    @RequestMapping("/menu/get/tree.json")
    // 获取所有的节点，并形成一颗树状返回
    public ResultEntity<Menu> getMenuTree() {

        // 获取所有节点
        List<Menu> menuList = menuService.getAllMenu();

        // 存储根节点
        Menu root = null;

        // 创建一个Map对象，用来存储节点id和节点对应的关系
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu: menuList) {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }

        // 遍历节点集合,建立节点间的父子节点关系
        for (Menu menu: menuList) {

            // 获取父节点的id
            Integer pid = menu.getPid();
            if (pid == null) {
                root = menu;
                continue;
            }

            // 根据父节点的id获取父节点
            Menu father = menuMap.get(pid);

            // 将子节点存入父节点的集合中
            father.getChildren().add(menu);
        }
        return ResultEntity.successWithDate(root);
    }

    // 保存Menu的方法
    @RequestMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(Menu menu) {
        menuService.saveMenu(menu);
        return ResultEntity.successWithOutDate();
    }
}
