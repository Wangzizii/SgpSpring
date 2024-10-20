package com.wz.mapper;

import cn.hutool.core.date.DateTime;
import com.wz.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface UserMapper {
        @Select("SELECT  * from user where username= #{username}")
        User findbyUsername(String username);

        @Select("SELECT  * from user where activationToken= #{activationToken}")
        User findbyActivationToken(String activationToken);

        @Insert("INSERT INTO user (username,password,create_time,update_time,activation_expiry,activationToken,email,nickname)" +
                "values (#{username},#{password},now(),now(),#{activation_expiry},#{activationToken},#{email},#{nickname})")
    void add(String username, String password, Date activation_expiry, String activationToken,String email,String nickname);

        @Update("UPDATE user SET active = true, activationToken = null, activation_expiry = null WHERE id = #{id}")
    void active(int id);

        @Update("UPDATE user SET  activationToken = #{activationToken}, activation_expiry = #{activation_expiry} WHERE id = #{id}")
    void reactivation(int id,String activationToken,Date activation_expiry);

        @Update("UPDATE user SET  secret=#{secret} WHERE username = #{username}")
    void setSecret(String username,String secret);

        @Update("UPDATE user SET  enable_authenticator=true WHERE username = #{username}")
    void setAuthenticator(String username);

        @Update("UPDATE  user SET  activationToken = null, activation_expiry = null,password=#{password} WHERE id=#{id}")
    void resetpassword(int id,String password);
}
