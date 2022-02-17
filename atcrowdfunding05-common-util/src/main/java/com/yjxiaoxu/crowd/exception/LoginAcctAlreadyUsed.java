package com.yjxiaoxu.crowd.exception;

/**
 * ClassName:LoginAcctAlreadyUsed
 * Package:com.yjxiaoxu.crowd.exception
 * Description:定义一个账号已经被使用国的异常类
 *
 * @Date:2021/9/9 17:01
 * @Author:小许33058485@qq.com
 */
public class LoginAcctAlreadyUsed extends RuntimeException {
    public LoginAcctAlreadyUsed() {}
    public LoginAcctAlreadyUsed(String s) {
        super(s);
    }
}
