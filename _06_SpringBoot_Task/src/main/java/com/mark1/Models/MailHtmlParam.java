package com.mark1.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailHtmlParam {
    private String fasongren;//邮件发送者
    private String fasongrenName;//发件人名称
    private String shoujianren;//收件人
    private String chaosongren;//抄送人
    private String youjianzhuti;//邮件主题
    private List<User> lisuser;
}
