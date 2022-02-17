package com.yjxiaoxu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClassName:Member_Auth_Consumer_7003
 * Package:com.yjxiaoxu.crowd
 * Description:
 *
 * @Date:2022/2/10 22:12
 * @Author:小许33058485@qq.com
 */
@SpringBootApplication                  // 开启spring配置
@EnableEurekaClient                     // 开启eureka客户端配置
public class Member_Auth_Consumer_7003 {

    public static void main(String[] args) {

        SpringApplication.run(Member_Auth_Consumer_7003.class, args);
    }
}
