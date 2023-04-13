package com.mark1.Job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mark1.Common.RandInfo;
import com.mark1.Models.MailHotSpotParam;
import com.mark1.Models.MailParam;
import com.mark1.Models.XxlUser;
import com.mark1.Service.EMail.MailService;
import com.mark1.Service.xxl.xxlService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class XxlUserJobHandler {
    private final static Logger logger = LoggerFactory.getLogger(XxlUserJobHandler.class);
    @Autowired
    private xxlService service;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @XxlJob("insertXxlUserData")
    public void insertXxlUserData()
    {
        RandInfo rand = new RandInfo();
        String familyName = rand.getFamilyName();
        String[] nameAndSex = rand.getNameAndSex(rand.getSex());
        String name = nameAndSex[0];
        String sex = nameAndSex[1];
        int age = rand.getAge();
        String phoneNumber = rand.getPhoneNum();
        String address = rand.getRoad();

        XxlUser xxlUser =new XxlUser();
        xxlUser.setName(familyName.concat(name));
        xxlUser.setAge(""+age);
        xxlUser.setSex(sex);
        xxlUser.setAddress(address);
        xxlUser.setPhonenumber(phoneNumber);

        service.XXLUserAdd(xxlUser);
    }

    @XxlJob("GetXxlUserDataEmail")
    public void GetXxlUserDataEmail()
    {
        try{
            String MailData = XxlJobHelper.getJobParam();
            logger.info("接收调度中心参数...[{}]",MailData);
            ObjectMapper xmlMapper = new XmlMapper();
            MailHotSpotParam mpm = xmlMapper.readValue(MailData, MailHotSpotParam.class);

            MailParam mailParam = new MailParam();
            mailParam.setCc(mpm.getChaosongren());
            mailParam.setTo(mpm.getShoujianren());
            mailParam.setFrom(mpm.getFajianren());
            mailParam.setFromName(mpm.getFajianrenName());
            mailParam.setSubject("XXLUSER表用户信息推送");
            Context context = new Context();
            context.setVariable("lisUser",service.XXLGetUserAll());

            String content = templateEngine.process("mailtemplate",context);

            mailParam.setContent(content);

            mailService.sendHtmlMail(mailParam);
            logger.info("XXLUSER表推送成功！");
        }
        catch (Exception ex)
        {
            logger.error("XXLUSER表推送失败！");
        }
    }
}
