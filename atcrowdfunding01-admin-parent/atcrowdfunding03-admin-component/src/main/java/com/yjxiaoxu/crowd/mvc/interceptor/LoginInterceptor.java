package com.yjxiaoxu.crowd.mvc.interceptor;

import com.yjxiaoxu.crowd.entity.Admin;
import com.yjxiaoxu.crowd.exception.AccessDeniedException;
import com.yjxiaoxu.crowd.util.constant.ConstantUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName:LoginInterceptor
 * Package:com.yjxiaoxu.crowd.mvc.interceptor
 * Description:定义一个拦截器类，用来检查用户是否已登录
 *
 * @Date:2021/8/20 17:12
 * @Author:小许33058485@qq.com
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 从session域中获取Admin对象，若Admin为空，则跳转到登录页面
        HttpSession session = httpServletRequest.getSession();
        Admin admin = (Admin) session.getAttribute(ConstantUtil.ATTR_NAME_ADMIN);
        if (admin == null) {
            throw new AccessDeniedException(ConstantUtil.MESSAGE_ACCESS_DENIED);
        }
        // 若Admin不为空，则放行
        return true;
    }

}
