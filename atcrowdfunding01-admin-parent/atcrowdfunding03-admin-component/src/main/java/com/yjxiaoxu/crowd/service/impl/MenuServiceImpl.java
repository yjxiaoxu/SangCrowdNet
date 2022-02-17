package com.yjxiaoxu.crowd.service.impl;

import com.yjxiaoxu.crowd.entity.Menu;
import com.yjxiaoxu.crowd.entity.MenuExample;
import com.yjxiaoxu.crowd.mapper.MenuMapper;
import com.yjxiaoxu.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:MenuServiceImpl
 * Package:com.yjxiaoxu.crowd.service.impl
 * Description:
 *
 * @Date:2021/9/23 20:21
 * @Author:小许33058485@qq.com
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menuList = menuMapper.selectByExample(new MenuExample());
        return menuList;
    }

    @Override
    public boolean saveMenu(Menu menu) {
        boolean flag = true;
        int count = menuMapper.insert(menu);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean editMenu(Menu menu) {
        boolean flag = true;
        int count = menuMapper.updateByPrimaryKeySelective(menu);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean removeMenu(Integer id) {
        boolean flag = true;
        int count = menuMapper.deleteByPrimaryKey(id);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }
}
