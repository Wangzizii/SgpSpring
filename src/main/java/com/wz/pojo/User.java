package com.wz.pojo;



import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    private String password;//密码
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
    private Date activation_expiry;
    private String activationToken;
    private boolean active;
    private String secret;
    private boolean enable_authenticator;
}
