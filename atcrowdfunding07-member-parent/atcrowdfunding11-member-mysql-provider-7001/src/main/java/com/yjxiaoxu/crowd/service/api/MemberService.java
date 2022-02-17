package com.yjxiaoxu.crowd.service.api;

import com.yjxiaoxu.crowd.entity.po.MemberPO;

/**
 * ClassName:MemberService
 * Package:com.yjxiaoxu.crowd.service.api
 * Description:
 *
 * @Date:2022/2/8 16:58
 * @Author:小许33058485@qq.com
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginacct);
}
