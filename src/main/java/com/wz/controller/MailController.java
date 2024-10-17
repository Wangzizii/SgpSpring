package com.wz.controller;

import cn.hutool.core.util.RandomUtil;
import com.wz.pojo.Result;
import com.wz.pojo.User;
import com.wz.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public Result sendMail(@RequestParam("to") String to,@RequestParam("subject")  String subject,@RequestParam("content")  String content) {
        try {
            System.out.println(to);
            mailService.sendMail(to, subject, content);
            return Result.success("接口调用成功,但不一定发送了");
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

    }

    @PostMapping("/sendEmailActivateUrl")
    public Result send(@RequestParam("to") String to, User user){
        try {
            System.out.println(to);
            Integer  uid=user.getId();
            String serverUrl="localhost:8080";
            String key = RandomUtil.randomString(10);
            String content = String.format("点击此链接进行激活： <a href=\"%s/user/activate?uid=%d&key=%s\">点我激活</a>", serverUrl, uid, key);
            mailService.sendMail(to, "验证", content);

            return Result.success("发送成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage()+"发送失败");
        }


    }
}
