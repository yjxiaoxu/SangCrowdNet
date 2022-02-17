package com.yjxiaoxu.crowd.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data                   // 相当于提供了setter、getter、equals、hasCode、toString方法
@NoArgsConstructor      // 相当于添加了无参的构造方法
@AllArgsConstructor     // 相当于添加的全参构造方法
public class MemberPO implements Serializable {
    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private Integer authstatus;

    private Integer usertype;

    private String realname;

    private String cardnum;

    private Integer accttype;

}