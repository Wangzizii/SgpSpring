package com.wz.service.impl;

import com.wz.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class MailServiceimpl implements MailService {
    private static final Logger logger= LoggerFactory.getLogger(MailServiceimpl.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    @Override
    public void checkMail(String to, String subject, String content) {
        if(StringUtils.isEmpty(to)){
            throw new RuntimeException("邮件收信人不能为空");
        }
        if(StringUtils.isEmpty(subject)){
            throw new RuntimeException("邮件主题不能为空");
        }
        if(StringUtils.isEmpty(content)){
            throw new RuntimeException("邮件内容不能为空");
        }


    }
    @Async("taskExecutor")
    @Override
    public void sendMail(String to, String subject, String content) {
        try {
            checkMail(to, subject, content);
            MimeMessageHelper message = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            message.setFrom(sendMailer);
            message.setSubject(subject);
            message.setTo(to);
            message.setText(content, true);
            message.setSentDate(new Date());


            javaMailSender.send(message.getMimeMessage());
            System.out.println("Success send");

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送失败"+e.getMessage());
        }

    }


}
