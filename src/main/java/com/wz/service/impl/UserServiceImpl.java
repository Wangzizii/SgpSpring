package com.wz.service.impl;

import com.wz.mapper.UserMapper;
import com.wz.pojo.User;
import com.wz.service.MailService;
import com.wz.service.UserService;
import com.wz.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.crypto.SecureUtil;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;
    @Override
    public User findbyUsername(String username) {

        User u= userMapper.findbyUsername(username);
        return u;
    }

    @Override
    public User findbyActivationToken(String token){
        User u= userMapper.findbyActivationToken(token);

        return u;

    }

    public void active(int id){
        userMapper.active(id);
    }

    @Override
    public void resendactivate(int id, String email) {
        String activationToken = UUIDUtil.getUUID();
        Date expiryTime = UUIDUtil.calculateExpiryTime(15);
        String activationLink = "http://13.229.104.127/auth/active/" + activationToken;


        String activecontent="<a href="+activationLink+">点我激活</a>";
        userMapper.reactivation(id,activationToken,expiryTime);

        mailService.sendMail(email,"Active your account",activecontent);


    }


    @Override
    public void register(String username, String password,String email,String nickname) {
        String activationToken = UUIDUtil.getUUID();
        Date expiryTime = UUIDUtil.calculateExpiryTime(15);
        String activationLink = "http://13.229.104.127/auth/active/" + activationToken;

        String activecontent="<a href="+activationLink+">Click me to active your account</a>";


        String md5String=SecureUtil.md5(password);
        userMapper.add(username,md5String,expiryTime,activationToken,email,nickname);

        mailService.sendMail(email,"Active your account",activecontent);

        return;

    }

    @Override
    public void forgetpassword(int id,String email) {
        String activationToken = UUIDUtil.getUUID();
        Date expiryTime = UUIDUtil.calculateExpiryTime(15);
        String resetLink= "http://13.229.104.127/auth/forget/" + activationToken;

        String activecontent="<a href="+resetLink+">Click me to reset your password</a>";
        mailService.sendMail(email,"Reset your password",activecontent);
        userMapper.reactivation(id,activationToken,expiryTime);

        return;
    }

    @Override
    public void resetpassword(int id,String password){
        String md5String=SecureUtil.md5(password);

        userMapper.resetpassword(id,md5String);
        return;

    }
}
