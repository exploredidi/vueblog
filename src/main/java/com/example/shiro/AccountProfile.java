package com.example.shiro;

import lombok.Data;

import java.io.Serializable;

/*
* 在AccountRealm我们还用到了AccountProfile，这是为了登录成功之后返回的一个用户信息的载体
* */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
    private String email;
}

