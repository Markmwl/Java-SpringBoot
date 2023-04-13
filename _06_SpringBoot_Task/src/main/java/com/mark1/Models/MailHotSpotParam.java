package com.mark1.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailHotSpotParam {
    private String fajianren;//邮件发送者
    private String fajianrenName;//发件人人名称
    private String shoujianren;//收件人
    private String chaosongren;//抄送人
}
