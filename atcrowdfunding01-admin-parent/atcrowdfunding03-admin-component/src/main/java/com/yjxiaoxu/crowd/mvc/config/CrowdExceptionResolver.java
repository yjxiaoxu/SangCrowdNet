package com.yjxiaoxu.crowd.mvc.config;

import com.google.gson.Gson;
import com.yjxiaoxu.crowd.exception.LoginAcctAlreadyUsed;
import com.yjxiaoxu.crowd.exception.LoginFailedException;
import com.yjxiaoxu.crowd.util.CrowdUtil;
import com.yjxiaoxu.crowd.util.ResultEntity;
import com.yjxiaoxu.crowd.util.constant.ConstantUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * ClassName:CrowdExceptionResolver
 * Package:com.yjxiaoxu.crowd.mvc.config
 * Description:定义一个异常处理类
 *
 * @Date:2021/8/17 15:50
 * @Author:小许33058485@qq.com
 */
@ControllerAdvice
public class CrowdExceptionResolver {
    // 新增admin时账号已存在异常处理方法
    @ExceptionHandler(LoginAcctAlreadyUsed.class)
    public ModelAndView loginAcctAlreadyUsed(Exception exception,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        String viewName = "admin-add";
        return resolverCommonException(viewName, exception, request, response);
    }
    // 管理员登录异常处理方法
    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView loginFailed(Exception exception,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return resolverCommonException(viewName, exception, request, response);
    }
    /**
     * 定义一个工具类，避免代码冗余，此工具方法只在本类使用
     */
    private ModelAndView resolverCommonException(String viewName,
                                                 Exception exception,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) throws IOException {
        //判断当前请求类型
        boolean requestType = CrowdUtil.judgeRequestType(request);
        //如果是ajax请求
        if (requestType) {
            //创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            //创建Gson对象;
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            //将json数据作为响应体返回给浏览器
            response.getWriter().write(json);
            //由于上面已经通过原生的response对象作出了相应，所以不提供ModelAndView对象
            return null;
        }
        //如果不是ajax请求，则创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        mv.addObject(ConstantUtil.ATTR_NAME_EXCEPTION, exception);
        mv.setViewName(viewName);
        return mv;
    }
}
