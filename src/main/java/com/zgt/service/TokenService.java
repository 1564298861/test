package com.zgt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zgt.entity.User;
import com.zgt.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发
 * @Title: TokenService.java
 * @author qiaoyn
 * @date 2019/06/14
 * @version V1.0
 */
@Service
public class TokenService {
    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000 * 24 * 7;//7天有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
