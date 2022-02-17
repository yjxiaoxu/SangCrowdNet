package com.yjxiaoxu.crowd.service.api;

import com.yjxiaoxu.crowd.entity.Menu;

import java.util.List;

/**
 * ClassName:MenuService
 * Package:com.yjxiaoxu.crowd.service.api
 * Description:
 *
 * @Date:2021/9/23 20:21
 * @Author:小许33058485@qq.com
 */
public interface MenuService {

    // 获取所有的Menu对象的集合
    List<Menu> getAllMenu();

    // 保存Menu的方法
    boolean saveMenu(Menu menu);

    // 修改Menu
    boolean editMenu(Menu menu);

    // 删除Menu
    boolean removeMenu(Integer id);
}
