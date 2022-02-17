package com.yjxiaoxu.crowd.mvc.config;

import com.yjxiaoxu.crowd.service.impl.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ClassName:CrowdSpringSecurityConfig
 * Package:com.yjxiaoxu.crowd.mvc.config
 * Description:定义一个专门与springSecurity配置相关的配置类
 *              自定义的SpringSecurity配置类需要继承WebSecurityConfigurerAdapter类
 *
 * @Date:2021/10/17 16:36
 * @Author:小许33058485@qq.com
 */
@Configuration
@EnableWebSecurity      // 开启web环境下的权限认证功能
public class CrowdSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // 重写configure(AuthenticationManagerBuilder auth)方法，配置security认证信息
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 基于内存的验证（测试）
//        auth.inMemoryAuthentication()
//                .withUser("tom")
//                .password("123")
//                .roles("ADMIN");

        // 启用基于数据库的认证
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());
    }

    // 重写configure(HttpSecurity http)方法，配置security认证策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()                    // 对请求进行授权
                .antMatchers("/index.jsp", "/bootstrap/**", "/crowd/**", "/css/**", "/ztree/**", "/fonts/**", "/img/**", "/jquery/**", "/layer/**", "/script/**")       // 针对某某路径进行授权
                .permitAll()                        // 对静态资源放行
                .anyRequest()                       // 其他任意请求
                .authenticated()                    // 认证后才能访问
                .and()
                .csrf()                             // 防跨站请求伪造功能
                .disable()                          // 禁用
                .formLogin()                        // 开启表单登录
                .loginPage("/admin/to/login/page.html")     // 指定登录页面
                .permitAll()                        // 对登陆页面放行
                .loginProcessingUrl("/security/do/login.html")   // 登录处理请求地址（表单要提交的地址）
                .defaultSuccessUrl("/admin/to/main/page.html")   // 登录成功后默认跳转的地址
                .and()
                .logout()                                   // 开启退出登录功能
                .logoutUrl("/security/do/logout.html")      // 指定处理退出登录功能地址
                .logoutSuccessUrl("/admin/to/login/page.html"); // 指定退出登录成功后跳转的地址

    }

    // 往SpringMVCIOC容器中添加BCryptPasswordEncoder对象
    // BCryptPasswordEncoder：springSecurity自带的一种密码加密算法
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
