package com.yjxiaoxu.crowd.api;

import com.yjxiaoxu.crowd.entity.po.MemberPO;
import com.yjxiaoxu.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:SqlRemoteService
 * Package:com.yjxiaoxu.crowd.api
 * Description:
 *
 * @Date:2022/2/9 16:12
 * @Author:小许33058485@qq.com
 */
@Component
@FeignClient(value = "crowd-member-mysql")
public interface SqlRemoteService {

    @RequestMapping("/member/get/member/by/loginAcct")
    public ResultEntity<MemberPO> getMemberPOByLoginAcct(@RequestParam("loginacct") String loginacct);
}
