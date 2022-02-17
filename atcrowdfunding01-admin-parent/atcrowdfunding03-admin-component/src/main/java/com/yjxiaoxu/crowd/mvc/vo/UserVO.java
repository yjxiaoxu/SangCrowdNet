package com.yjxiaoxu.crowd.mvc.vo;

import com.yjxiaoxu.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * ClassName:UserVo
 * Package:com.yjxiaoxu.crowd.mvc.vo
 * Description:定义一个VO类，用来继承UserDetails接口的实现类（User类），目的是对其进行封装
 *              因为User只有常用的username, password等属性，不能满足我们的功能需求
 *              如：登录后在主页面显示出当前用户的Name
 *
 *              注意：User只有两个有参的构造方法，没有无参的构造方法，
 *                      所以在编写VO类时，要调用父类的有参的构造方法，因为在创建对象时，先创建父类的对象
 * @Date:2021/10/19 16:30
 * @Author:小许33058485@qq.com
 */
public class UserVO extends User {

    // 保存我们自己查询的Admin对象的引用,方便我们获取Admin的相关信息
    private Admin admin;

    public UserVO(Admin admin, List<GrantedAuthority> authorityList) {
        // 调用父类的构造器
        super(admin.getLoginAcct(), admin.getUserPswd(), authorityList);
        this.admin = admin;

        // 擦除原始admin的密码
        this.admin.setUserPswd(null);
    }

    // getter方法
    public Admin getAdmin() {
        return admin;
    }
}
