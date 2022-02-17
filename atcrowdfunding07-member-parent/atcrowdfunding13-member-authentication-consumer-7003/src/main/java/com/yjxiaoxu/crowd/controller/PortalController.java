package com.yjxiaoxu.crowd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:PortalController
 * Package:com.yjxiaoxu.crowd.controller
 * Description:
 *
 * @Date:2022/2/10 22:39
 * @Author:小许33058485@qq.com
 */
@Controller
public class PortalController {

    @RequestMapping("/")
    public String toPortalPage() {

        return "index";
    }
}
