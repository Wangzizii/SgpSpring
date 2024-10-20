package com.wz.service;

import com.wz.pojo.User;

public interface UserService {
    User findbyUsername(String username);

    User findbyActivationToken(String token);

    void resendactivate(int id, String email);

    void register(String username, String password, String email, String nickname);

    void active(int id);

    void forgetpassword(int id,String email);

    void  resetpassword(int id,String password);

}
