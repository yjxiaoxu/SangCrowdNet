package com.yjxiaoxu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * ClassName:Member_Zuul_80
 * Package:com.yjxiaoxu.crowd
 * Description:
 *
 * @Date:2022/2/10 21:23
 * @Author:小许33058485@qq.com
 */
@SpringBootApplication          // 开启spring配置
@EnableZuulProxy                // 开启zuul配置
public class Member_Zuul_80 {

    public static void main(String[] args) {

        SpringApplication.run(Member_Zuul_80.class, args);
    }
}
