package com.example.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.example.Utils.JwtUtils;
import com.example.entity.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/*
* AccountRealm是shiro进行登录或者权限校验的逻辑所在
* */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    /*
    * supports：为了让realm支持jwt的凭证校验
    * */
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    /*
    * shiro默认supports的是UsernamePasswordToken，
    * 而我们现在采用了jwt的方式，
    *
    * 所以我们在shiro包中自定义一个JwtToken，来完成shiro的supports方法。
    * */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /*
    * doGetAuthorizationInfo：权限校验
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /*
    * doGetAuthenticationInfo：登录认证校验
    * */

    /*
    * 主要就是doGetAuthenticationInfo登录认证这个方法，可以看到我们通过jwt获取到用户信息，
    * 判断用户的状态，最后异常就抛出对应的异常信息，
    * 或者封装成SimpleAuthenticationInfo返回给shiro
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwt = (JwtToken) authenticationToken;
        log.info("jwt----------------->{}", jwt);
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        User user = userService.getById(Long.parseLong(userId));
        if(user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if(user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定！");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);
        log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }

    }

