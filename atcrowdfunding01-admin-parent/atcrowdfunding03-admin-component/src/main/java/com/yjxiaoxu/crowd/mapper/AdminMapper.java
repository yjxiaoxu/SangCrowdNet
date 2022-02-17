package com.yjxiaoxu.crowd.mapper;


import com.yjxiaoxu.crowd.entity.Admin;
import com.yjxiaoxu.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    // 根据关键字查询符合相关条件的Admin
    List<Admin> selectByKeyWord(String keyWord);

    void purgeAdminAndRoleRelationship(Integer adminId);

    void saveAdminAndRoleRelationship(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}