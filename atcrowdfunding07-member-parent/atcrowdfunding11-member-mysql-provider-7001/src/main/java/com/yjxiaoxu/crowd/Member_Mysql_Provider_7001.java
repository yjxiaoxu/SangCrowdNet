package com.yjxiaoxu.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClassName:Member_Mysql_Provider_7001
 * Package:com.yjxiaoxu.crowd
 * Description:
 *
 * @Date:2022/2/7 23:47
 * @Author:小许33058485@qq.com
 */
@MapperScan("com.yjxiaoxu.crowd.mapper")
@SpringBootApplication      // 开启spring配置
@EnableEurekaClient         // 开启eureka客户端配置
public class Member_Mysql_Provider_7001 {

    public static void main(String[] args) {

        SpringApplication.run(Member_Mysql_Provider_7001.class, args);
    }
}
