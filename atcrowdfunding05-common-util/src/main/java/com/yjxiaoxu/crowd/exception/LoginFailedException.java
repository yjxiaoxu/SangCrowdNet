package com.yjxiaoxu.crowd.exception;

/**
 * ClassName:LoginFailedException
 * Package:com.yjxiaoxu.crowd.exception
 * Description:
 *
 * @Date:2021/8/19 21:02
 * @Author:小许33058485@qq.com
 */
public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {}
    public LoginFailedException(String exception) {
        super(exception);
    }
}
