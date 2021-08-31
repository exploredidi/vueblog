package com.example.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * JwtUtils是个生成和校验jwt的工具类，其中有些jwt相关的密钥信息是从项目配置文件（application.yml）中配置的
 * */

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "config.jwt")
public class JwtUtils {

    private String secret;
    private long expire;
    private String header;
    /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        Date date = new Date();

        Date expireDate = new Date(date.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userId+"")
                .setIssuedAt(date)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    // 获取jwt的信息
    public Claims getClaimByToken(String token) {
            try {
                return Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token)
                        .getBody();
            }catch (Exception e){
                log.debug("validate is token error",e);
                return null;
            }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
