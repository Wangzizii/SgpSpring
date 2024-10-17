package com.wz.service;

public interface AuthenticatorService {

    public String generateAuthUrl(String username,boolean enable);
    public boolean validateCode(String username, int code,boolean enable,String secret);

}
