package com.mark1.Service.EMail;


import com.mark1.Models.MailFileParam;
import com.mark1.Models.MailParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    //方法5个参数分别表示：邮件发送者、收件人、抄送人、邮件主题以及邮件内容
    public void sendSimpleMail(MailParam mailParam) {
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        simpMsg.setFrom(mailParam.getFrom());
        simpMsg.setTo(mailParam.getTo());
        if (mailParam.getCc() != null && !"".equals(mailParam.getCc()))
        {
            simpMsg.setCc(mailParam.getCc());
        }
        simpMsg.setSubject(mailParam.getSubject());
        simpMsg.setText(mailParam.getContent());
        javaMailSender.send(simpMsg);
    }

    //方法5个参数分别表示：邮件发送者、收件人、抄送人、邮件主题以及邮件内容
    public void sendMimeMail(MailParam mailParam) throws Exception {
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        MimeMessage message = javaMailSender.createMimeMessage();
        // 这里使用 MimeMessageHelper 简化了邮件配置
        // 第二个参数true表示构造一个 multipart message 类型邮件
        // multipart message类型邮件包含多个正文、附件以及内嵌资源，邮件表现形式更加丰富
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailParam.getFrom());
        helper.setTo(mailParam.getTo());
        if (mailParam.getCc() != null && !"".equals(mailParam.getCc()))
        {
            helper.setCc(mailParam.getCc());
        }
        helper.setSubject(mailParam.getSubject());
        helper.setText(mailParam.getContent());
        javaMailSender.send(message);
    }

    // 发送带附件的邮件
    // 方法5个参数分别表示：邮件发送者、收件人、邮件主题、邮件内容、以及附件
    public void sendAttachFileMail(MailFileParam mailParam) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        // 这里使用 MimeMessageHelper 简化了邮件配置
        // 第二个参数true表示构造一个 multipart message 类型邮件
        // multipart message类型邮件包含多个正文、附件以及内嵌资源，邮件表现形式更加丰富
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if (mailParam.getFromName() != null && !"".equals(mailParam.getFromName()) && !"null".equals(mailParam.getFromName()))
        {
            helper.setFrom(mailParam.getFrom(),mailParam.getFromName());
        }
        else
        {
            helper.setFrom(mailParam.getFrom());
        }
        helper.setTo(mailParam.getTo());
        if (mailParam.getCc() != null && !"".equals(mailParam.getCc()))
        {
            helper.setCc(mailParam.getCc());
        }
        helper.setSubject(mailParam.getSubject());
        helper.setText(mailParam.getContent());
        // 最后通过 addAttachment 方法添加附件
        helper.addAttachment(mailParam.getFile(),new File(mailParam.getFile()));
        javaMailSender.send(message);
    }

    // 发送正文是html的邮件
    // 方法5个参数分别表示：邮件发送者、收件人、邮件主题、邮件内容、以及附件
    public void sendHtmlMail(MailParam mailParam) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        // 这里使用 MimeMessageHelper 简化了邮件配置
        // 第二个参数true表示构造一个 multipart message 类型邮件
        // multipart message类型邮件包含多个正文、附件以及内嵌资源，邮件表现形式更加丰富
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        if (mailParam.getFromName() != null && !"".equals(mailParam.getFromName()) && !"null".equals(mailParam.getFromName()))
        {
            helper.setFrom(mailParam.getFrom(),mailParam.getFromName());
        }
        else
        {
            helper.setFrom(mailParam.getFrom());
        }
        helper.setTo(mailParam.getTo());
        if (mailParam.getCc() != null && !"".equals(mailParam.getCc()) && !"null".equals(mailParam.getCc()))
        {
            helper.setCc(mailParam.getCc());
        }
        helper.setSubject(mailParam.getSubject());
        helper.setText(mailParam.getContent(),true);
        javaMailSender.send(message);
    }
}
