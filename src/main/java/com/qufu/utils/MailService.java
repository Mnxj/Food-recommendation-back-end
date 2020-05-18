package com.qufu.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    public int sendSimpleMail(String to,
                              String subject, String content) {
        int i = 0;
        try {
            System.out.println(to + "---" + subject + "----" + content);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);//添加附件
            helper.setFrom(from);//邮件发送者
            helper.setTo(to);//收件人，
            // message.setCc(cc);//抄送人
            helper.setSubject(subject);//邮件主题
            helper.setText(content, true);//邮件内容
            //  helper.addAttachment(file.getName(),file);//上传附件
            javaMailSender.send(message);
            System.out.println(1);
            i = 1;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return i;
    }


}
