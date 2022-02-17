package com.yjxiaoxu.crowd.controller;

import com.yjxiaoxu.crowd.entity.po.MemberPO;
import com.yjxiaoxu.crowd.service.api.MemberService;
import com.yjxiaoxu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:MemberController
 * Package:com.yjxiaoxu.crowd.controller
 * Description:
 *
 * @Date:2022/2/8 17:03
 * @Author:小许33058485@qq.com
 */
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/member/get/member/by/loginAcct")
    public ResultEntity<MemberPO> getMemberPOByLoginAcct(@RequestParam("loginacct") String loginacct) {
        try {

            // 调用service方法，根据账号获取会员对象
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);

            // 程序执行到此处说明没有抛出异常，将查询到的会员对象以json格式返回
            return ResultEntity.successWithDate(memberPO);
        } catch(Exception e) {

            // 打印异常信息
            e.printStackTrace();

            // 程序执行到此处说明查询出现异常，返回查询失败结果
            return ResultEntity.failed(e.getMessage());
        }
    }
}
