package com.yjxiaoxu.crowd.exception;

/**
 * ClassName:AccessDeniedException
 * Package:com.yjxiaoxu.crowd.exception
 * Description:
 *
 * @Date:2021/8/20 17:20
 * @Author:小许33058485@qq.com
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {}
    public AccessDeniedException(String exception){
        super(exception);
    }
}
