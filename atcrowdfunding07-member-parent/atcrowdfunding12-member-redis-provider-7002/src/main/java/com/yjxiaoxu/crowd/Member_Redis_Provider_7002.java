package com.yjxiaoxu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClassName:Member_Redis_Provider_7002
 * Package:com.yjxiaoxu.crowd
 * Description:
 *
 * @Date:2022/2/9 16:32
 * @Author:小许33058485@qq.com
 */
@EnableEurekaClient             // 开启eureka客户端配置
@SpringBootApplication          // 开启spring配置
public class Member_Redis_Provider_7002 {

    public static void main(String[] args) {
        SpringApplication.run(Member_Redis_Provider_7002.class, args);
    }
}
