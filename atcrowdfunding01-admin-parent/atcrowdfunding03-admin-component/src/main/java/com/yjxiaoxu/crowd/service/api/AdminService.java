package com.yjxiaoxu.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.yjxiaoxu.crowd.entity.Admin;

import java.util.List;

/**
 * ClassName:AdminService
 * Package:com.yjxiaoxu.crowd.service.api
 * Description:
 *
 * @Date:2021/8/13 15:19
 * @Author:小许33058485@qq.com
 */
public interface AdminService {
    //保存管理者的方法
    int saveAdmin(Admin admin);

    List<Admin> getAll();

    // admin登录方法
    Admin getAdminByAccount(String username, String password);

    // 根据关键字查询出符合条件的Admin
    PageInfo<Admin> selectByKeyWord(String keyWord, Integer pageNum, Integer pageSize);

    // 根据Admin的id删除Admin
    boolean deleteByPrimaryKey(Integer key);

    // 根据Id获取Admin
    Admin getAdminByAdminId(Integer adminId);

    boolean editAdminByAdminId(Admin admin);

    void saveAdminAndRoleRelationship(Integer adminId, List<Integer> roleIdList);

    // 根据Admin的username获取Admin
    Admin getAdminByUsername(String username);
}
