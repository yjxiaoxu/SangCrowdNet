package com.yjxiaoxu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ClassName:Member_Eureka_7000
 * Package:com.yjxiaoxu.crowd
 * Description:
 *
 * @Date:2022/2/7 20:05
 * @Author:小许33058485@qq.com
 */
@SpringBootApplication      // 开启spring配置
@EnableEurekaServer         // 开启eureka服务端配置
public class Member_Eureka_7000 {

    public static void main(String[] args) {

        SpringApplication.run(Member_Eureka_7000.class, args);
    }
}
