package com.mark1.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailFileParam {
    private String from;//邮件发送者
    private String fromName;//发件人人名称
    private String to;//收件人
    private String cc;//抄送人
    private String subject;//邮件主题
    private String content;//邮件内容
    private String file; //附件
}
