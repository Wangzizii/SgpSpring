package com.wz.interceptors;

import com.wz.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
@CrossOrigin
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        String token=request.getHeader("Authorization");


        try {
            JWTUtil.verifyToken(token);
            System.out.println("token验证成功");
            return true;
        }
        catch (Exception e) {
            response.setStatus(401);
            System.out.println(e.getMessage());
            System.out.println("失败");

            return false;
        }

    }
}
