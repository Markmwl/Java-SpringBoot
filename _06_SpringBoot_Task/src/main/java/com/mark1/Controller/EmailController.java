package com.mark1.Controller;

import com.mark1.Models.MailFileParam;
import com.mark1.Models.MailParam;
import com.mark1.Service.EMail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/SendMail")
    public void SendMail(MailParam mailParam) throws Exception {
//        mailService.sendSimpleMail("123@QQ.com",
//                "1421548723@qq.com",
//                "123456@qq.com",
//                "我是邮件的标题",
//                "我是邮件的内容");
        //mailService.sendSimpleMail(mailParam);
        mailService.sendMimeMail(mailParam);
    }

    @GetMapping("/SendMailFile")
    public void SendMailFile(MailFileParam mailParam) throws Exception {
//        mailService.sendAttachFileMail("123@QQ.com",
//                "1421548723@qq.com",
//                "我是邮件的标题",
//                "我是邮件的内容",
//                new File("C:/Users/Administrator/Pictures/Saved Pictures/fthsrethstrywegwerrg5.jpg"));
        mailService.sendAttachFileMail(mailParam);
    }
}
