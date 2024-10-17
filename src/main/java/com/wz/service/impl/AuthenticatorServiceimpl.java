package com.wz.service.impl;

import com.wz.mapper.UserMapper;
import com.wz.pojo.User;
import com.wz.service.AuthenticatorService;
import com.wz.utils.GoogleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
@Service
public class AuthenticatorServiceimpl implements AuthenticatorService {
    @Autowired
    private UserMapper userMapper;
    private Map<String, String> userKeys = new HashMap<>();
    @Override
    public String generateAuthUrl(String username,boolean  enable) {
        if(enable){
            return null;

        }
        else{
            String secret = GoogleGenerator.generateSecretKey();

            userKeys.put(username, secret);
            userMapper.setSecret(username, secret);

            return GoogleGenerator.getQRBarcode(username,secret);
        }


    }



    @Override
    public boolean validateCode(String username, int code, boolean enable,String secret) {
        boolean ret = GoogleGenerator.checkCode(secret, code);
        if(!enable&&ret){
            userMapper.setAuthenticator(username);
        }

        return ret;

    }
}
