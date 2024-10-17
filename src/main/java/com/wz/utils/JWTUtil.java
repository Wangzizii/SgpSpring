package com.wz.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    public static String key="wzcool";

    public static String generateToken(Map<String,Object> claims) {

        return  JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*24))
                .sign(Algorithm.HMAC256(key));




    }

    public static Map<String, Object> verifyToken(String token) {

        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}

