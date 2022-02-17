package com.yjxiaoxu.crowd.mapper;

import com.yjxiaoxu.crowd.entity.Auth;
import com.yjxiaoxu.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> getAuthIdListByRoleId(Integer roleId);

    void purgeRoleAndAuthRelationship(Integer roleId);

    void saveRoleAndAuthRelationship(@Param("roleId")Integer roleId, @Param("authIdList")List<Integer> authIdList);

    List<String> getAssignedAuthNameListByAdminId(Integer adminId);
}