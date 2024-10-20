package com.wz.controller;

import cn.hutool.crypto.SecureUtil;
import com.wz.pojo.Result;
import com.wz.pojo.User;
import com.wz.service.UserService;
import com.wz.service.impl.AuthenticatorServiceimpl;
import com.wz.utils.JWTUtil;
import com.wz.utils.UUIDUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticatorServiceimpl authenticatorService;

    @PostMapping("/register")
    public Result  register(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$")
    String password,String email,String nickname){
        try {
        System.out.println("gotit");
        User u =userService.findbyUsername(username);



        if(u==null){
            userService.register(username,password,email,nickname);
            System.out.println("mail sended");

         return Result.success("Success,We send an activation link to your email");
        }
        else {
            return  Result.error("Username already exist");
        }
        }
        catch (Exception e) {
            e.printStackTrace();
            return  Result.error(e.getMessage());
        }



    }
    @PostMapping("/reactvie")
    public Result reactvie(String username){
        User u=userService.findbyUsername(username);
        userService.resendactivate(u.getId(),u.getEmail());
     return Result.success("Success");
    }

    @PostMapping("/forget")
    public Result forget(String username){
        User u=userService.findbyUsername(username);
        if(u!=null&&u.isActive()){
            userService.forgetpassword(u.getId(),u.getEmail());

            return Result.success("Please check email");
        }
        else {
            return Result.error("Username is not exist");
        }
    }

    @PostMapping("/forget/{token}")
    public Result forgetpassword(@PathVariable String token){
        User u=userService.findbyActivationToken(token);
        if(u!=null&&u.isActive()){
            return Result.success("Reset your password");
        }
        return  Result.error("Token is not exist");

    }
    @PostMapping("/forget/reset")
    public Result forgetreset(String token,@Pattern(regexp = "^\\S{5,16}$") String password){
        User u=userService.findbyActivationToken(token);
        if(u!=null&&u.isActive()){

            userService.resetpassword(u.getId(),password);
            return Result.success("Success Reset your password");


        }
        return  Result.error("Please enter by your email link");

    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User u  =userService.findbyUsername(username);
        Map<String,Object> data = new HashMap<>();

        if(u==null){
            return  Result.error("Username isn't exist");
        } else if (!u.isActive()) {
            return  Result.error("Please activate your account first");

        }
        else{
            if(u.getPassword().equals(SecureUtil.md5(password))){
                if(u.isEnable_authenticator()){
                    data.put("authenticator",true);
                    data.put("username",u.getUsername());
                    return Result.success("Success",data);
                }

                else{
                Map<String,Object> claims = new HashMap<>();
                claims.put("username",u.getUsername());
                claims.put("password",u.getPassword());
                String token= JWTUtil.generateToken(claims);
                System.out.println(u.isActive());
                data.put("token",token);
                data.put("authenticator",false);
                data.put("username",u.getUsername());
                return Result.success("Success",data);
            }
            }
            else {
                return  Result.error("Wrong password");
            }
        }

    }
    @PostMapping("/twostep")
    public Result twostep(String username , int code){

        User u=userService.findbyUsername(username);

        if(u.isEnable_authenticator()){

            if(authenticatorService.validateCode(username,code,true,u.getSecret())){
                Map<String,Object> claims = new HashMap<>();
                claims.put("username",u.getUsername());
                claims.put("password",u.getPassword());
                String token= JWTUtil.generateToken(claims);

            return Result.success("Success",token);
            }
            else{
            return  Result.error("Wrong code");
            }
        }
        else {
            if(authenticatorService.validateCode(username,code,false,u.getSecret())){
                return Result.success("Success",true);
            }
            else {
                return  Result.error("Wrong code");
            }

        }
    }


    @GetMapping("/active/{token}")
    public Result userActive(@PathVariable String token){
        User u =userService.findbyActivationToken(token);
        if(u!=null&&u.getActivationToken().equals(token)&&!u.isActive()){
            Date now = new Date();
            if(u.getActivation_expiry().after(now)){
                userService.active(u.getId());
                return  Result.success("Success",u.getUsername());
            }
            else {
                return  Result.error(500,"Link expired",u.getUsername());
            }
        }
        else {
            return Result.error("Activation token is invalid");
        }
    }


    @PostMapping("/twostepbind")
    public Result twostepbind(String username){
        User u= userService.findbyUsername(username);
        if(u!=null&&u.getActivationToken()==null){
        String secretlink=authenticatorService.generateAuthUrl(username,u.isEnable_authenticator());
            return Result.success("Scan this Qrcode",secretlink);
        }
        else{
            return  Result.error("Username isn't exist or Please activate your account first");
        }

    }
    @PostMapping("/checkmfa")
    public Result checktwostep(String username){
        User u=userService.findbyUsername(username);
        if (u.isEnable_authenticator()){
            return  Result.success("Success",true);
        }
        else{
            return  Result.success("Success",false);
        }
    }

    @PostMapping("/checklogin")
    public Result<String> content(HttpServletResponse response) {
        return Result.success("I'm Content");
    }

}
