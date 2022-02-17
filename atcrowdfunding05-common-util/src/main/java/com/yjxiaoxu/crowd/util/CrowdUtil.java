package com.yjxiaoxu.crowd.util;

import com.yjxiaoxu.crowd.util.constant.ConstantUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName:CrowdUtil
 * Package:com.yjxiaoxu.crowd.util
 * Description:定义一个通用工具类
 *
 * @Date:2021/8/17 15:20
 * @Author:小许33058485@qq.com
 */
public class CrowdUtil {

    /**
     * 判断当前的请求是否是ajax请求
     * @param request
     * @return true:ajax请求，false:普通请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        //获取请求头
        // 1.获取请求消息头信息
        String acceptInformation = request.getHeader("Accept");
        String xRequestInformation = request.getHeader("X-Requested-With");
        return
                (
                acceptInformation != null
                    &&
                acceptInformation.contains("application/json")
                )
                ||
                (
                xRequestInformation != null
                    &&
                xRequestInformation.equals("XMLHttpRequest")
                );

    }
    public static String md5(String source) {
        // 1.判断 source 是否有效
        if(source == null || source.length() == 0) {
        // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException(ConstantUtil.MESSAGE_STRING_INVALIDATE);
        }
        try {
        // 3.获取 MessageDigest 对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
        // 5.执行加密
            byte[] output = messageDigest.digest(input);
        // 6.创建 BigInteger 对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
        // 7.按照 16 进制将 bigInteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}