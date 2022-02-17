package com.yjxiaoxu.crowd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:SysTimeUtil
 * Package:com.yjxiaoxu.crowd.util
 * Description:定义一个工具类，获取系统当前时间
 *
 * @Date:2021/9/8 20:37
 * @Author:小许33058485@qq.com
 */
public class SysTimeUtil {
    // 工具类的构造方法一般私有化
    private SysTimeUtil() {}
    private static SimpleDateFormat simpleDateFormat;
    // 获取系统当前时间的方法
    public static String getSysTime() {
        Date date = new Date();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
