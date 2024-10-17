package com.wz.service;

public interface MailService {
    public void checkMail(String to, String subject, String content);
    public void sendMail(String to, String subject, String content);
}
