package com.yjxiaoxu.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxiaoxu.crowd.entity.Admin;
import com.yjxiaoxu.crowd.entity.AdminExample;
import com.yjxiaoxu.crowd.exception.LoginAcctAlreadyUsed;
import com.yjxiaoxu.crowd.exception.LoginFailedException;
import com.yjxiaoxu.crowd.mapper.AdminMapper;
import com.yjxiaoxu.crowd.service.api.AdminService;
import com.yjxiaoxu.crowd.util.CrowdUtil;
import com.yjxiaoxu.crowd.util.SysTimeUtil;
import com.yjxiaoxu.crowd.util.constant.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ClassName:AdminServiceImpl
 * Package:com.yjxiaoxu.crowd.service.impl
 * Description:
 *
 * @Date:2021/8/13 15:20
 * @Author:小许33058485@qq.com
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public int saveAdmin(Admin admin) {
        int count = 0;
        // 使用MD5算法对密码进行加密
//        String userPswdMd5 = CrowdUtil.md5(admin.getUserPswd());
        String userPswdBCrypt = bCryptPasswordEncoder.encode(admin.getUserPswd());
        admin.setUserPswd(userPswdBCrypt);
        String createTime = SysTimeUtil.getSysTime();
        admin.setCreateTime(createTime);
        try {
            count = adminMapper.insert(admin);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyUsed(ConstantUtil.MESSAGE_LOGIN_ACCT_ALREADY_USED);
            }
        }
        return count;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> adminList = adminMapper.selectByExample(new AdminExample());
        int i = 10 / 0;
        return adminList;
    }

    @Override
    public Admin getAdminByAccount(String username, String password) {
        // 根据用户输入的账号查询Admin对象
            // 创建AdminExample对象
        AdminExample adminExample = new AdminExample();
            // 创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
            // 在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(username);
            // 调用adminMapper的查询方法查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 如果Admin为空，抛出异常
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(ConstantUtil.MESSAGE_LOGIN_FAILED);
        }
        //账号不唯一，系统错误
        if (admins.size() > 1) {
            throw new LoginFailedException(ConstantUtil.MESSAGE_LOGIN_SYSTEM_LOGINACCT_NOT_UNIQUE);
        }
        Admin admin = admins.get(0);
        if (admin == null) {
            throw new LoginFailedException(ConstantUtil.MESSAGE_LOGIN_FAILED);
        }
        // 如果Admin不为空，获取其加密后的密码，将用户输入的密码进行加密后与其进行比对
        String userPwsdFromDB = admin.getUserPswd();
        String userPwsdFromMD5 = CrowdUtil.md5(password);
        if (! Objects.equals(userPwsdFromDB, userPwsdFromMD5)) {
            throw new LoginFailedException(ConstantUtil.MESSAGE_LOGIN_FAILED);
        }
        // 如果密码正确，则返回Admin对象
        // 如果密码错误，则抛出异常
        return admin;
    }

    @Override
    public PageInfo<Admin> selectByKeyWord(String keyWord, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectByKeyWord(keyWord);

        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteByPrimaryKey(Integer key) {
        boolean flag = true;

        // 在删除Admin之前先删除Admin和role之间的关系
        adminMapper.purgeAdminAndRoleRelationship(key);

        int count = adminMapper.deleteByPrimaryKey(key);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Admin getAdminByAdminId(Integer adminId) {
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        return admin;
    }

    @Override
    public boolean editAdminByAdminId(Admin admin) {
        boolean flag = true;
        int count = adminMapper.updateByPrimaryKey(admin);
        if (count != 1) {
          flag = false;
        }
        return flag;
    }

    @Override
    public void saveAdminAndRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        // 在给Admin分配角色前先把它之前分配的角色全部删除
        adminMapper.purgeAdminAndRoleRelationship(adminId);

        // 在执行分配角色操作前要先判断用户是否给当前用户分配了角色
        if (roleIdList != null && roleIdList.size() > 0) {

            // 根据用户的要求给选中的Admin分配相应的角色
            adminMapper.saveAdminAndRoleRelationship(adminId, roleIdList);
        }
    }

    @Override
    public Admin getAdminByUsername(String username) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(username);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        Admin admin = null;
        if (adminList.size() > 0) {
            admin = adminList.get(0);
        }
        return admin;
    }


}
