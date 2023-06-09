package com.deng.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    private static String signKey = "QiusongDeng";
    private static Long expire = 3600 * 1000L;

    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS256, signKey).setExpiration(new Date(System.currentTimeMillis() + expire)).compact();
        return jwt;
    }
    public static Claims parseJwt(String jwt){
        Claims claims=Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt).getBody();
        return claims;
    }
}
