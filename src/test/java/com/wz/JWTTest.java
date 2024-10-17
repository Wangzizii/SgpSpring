package com.wz;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {


    public void generateToken() {
     String key="wzcool";
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", "1");
        claims.put("username", "test");
        claims.put("password", "test");

        String token= com.auth0.jwt.JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+100*60*60*6))
                .sign(Algorithm.HMAC256(key));
        System.out.println(token);



    }

    public void verifyToken() {
        String key="wzcool";
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsicGFzc3dvcmQiOiJ0ZXN0IiwiaWQiOiIxIiwidXNlcm5hbWUiOiJ0ZXN0In0sImV4cCI6MTcyNTYxMzIxOX0.Z9vXojffbwDapHgdI7kFyavfJem6isHLVloV_tInG5Y";
        System.out.println(JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap());
    }
}
