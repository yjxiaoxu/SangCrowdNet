package com.yjxiaoxu.crowd.service.impl;

import com.yjxiaoxu.crowd.entity.po.MemberPO;
import com.yjxiaoxu.crowd.entity.po.MemberPOExample;
import com.yjxiaoxu.crowd.mapper.MemberPOMapper;
import com.yjxiaoxu.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:MemberServiceImpl
 * Package:com.yjxiaoxu.crowd.service.impl
 * Description:
 *
 * @Date:2022/2/8 16:59
 * @Author:小许33058485@qq.com
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {

        // 创建Example对象
        MemberPOExample memberPOExample = new MemberPOExample();

        // 创建Criteria对象
        MemberPOExample.Criteria criteria = memberPOExample.createCriteria();

        // 封装查询条件
        criteria.andLoginacctEqualTo(loginacct);

        // 执行查询
        List<MemberPO> list = memberPOMapper.selectByExample(memberPOExample);
        return list.get(0);
    }
}
