package com.mark1.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailParam {
    private String from;//邮件发送者
    private String fromName;//邮件发送者名称
    private String to;//收件人
    private String cc;//抄送人
    private String subject;//邮件主题
    private String content;//邮件内容
}
