package com.mark.Controller.Email;

import com.mark.Common.Result.RestResult;
import com.mark.Common.Result.ResultStatus;
import com.mark.Models.MailFileParam;
import com.mark.Models.MailHtmlParam;
import com.mark.Models.MailParam;
import com.mark.Service.EMail.MailService;
import com.mark.WebApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "发送邮件模块")
public class EmailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @ApiOperation("发送邮件")
    @GetMapping("/SendMail")
    public RestResult SendMail(MailParam mailParam) {
        try{
            //mailService.sendSimpleMail(mailParam);
            mailService.sendMimeMail(mailParam);
            return new RestResult(ResultStatus.OK,"发送成功");
        }
        catch (Exception ex)
        {
            return new RestResult(ResultStatus.NG,"发送失败!原因："+ex.getMessage());
        }

    }

    @ApiOperation("发送邮件带附件")
    @GetMapping("/SendMailFile")
    public RestResult SendMailFile(MailFileParam mailParam) {
        try{
            mailService.sendAttachFileMail(mailParam);
            return new RestResult(ResultStatus.OK,"发送成功");
        }
        catch (Exception ex)
        {
            return new RestResult(ResultStatus.NG,"发送失败!原因："+ex.getMessage());
        }
    }

    @ApiOperation("发送Ftl正文html邮件")
    @PostMapping("/SendFtlMailHtml")
    public RestResult SendFtlMailHtml(@Validated @RequestBody MailHtmlParam mailParam) {
        try{
            MailParam pm = new MailParam();
            pm.setFrom(mailParam.getFasongren());
            pm.setFromName(mailParam.getFasongrenName());
            pm.setTo(mailParam.getShoujianren());
            pm.setCc(mailParam.getChaosongren());
            pm.setSubject(mailParam.getYoujianzhuti());

            //配置 freemarker 模板位置
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            ClassLoader classLoader = WebApplication.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(classLoader,"ftl");

            //配置模板文件
            Template template = configuration.getTemplate("mailtemplate.ftl");

            //结合User对象渲染模板
            StringWriter stringWriter = new StringWriter();
            Map<String,Object> root=new HashMap<String,Object>();
            root.put("lisUser",mailParam.getLisuser());
            template.process(root,stringWriter);

            pm.setContent(stringWriter.toString());

            mailService.sendHtmlMail(pm);
            return new RestResult(ResultStatus.OK,"发送成功");
        }
        catch (Exception ex)
        {
            return new RestResult(ResultStatus.NG,"发送失败!原因："+ex.getMessage());
        }
    }

    @ApiOperation("发送正文html邮件")
    @PostMapping("/SendMailHtml")
    public RestResult SendMailHtml(@Validated @RequestBody MailHtmlParam mailParam) {
        try{
            MailParam pm = new MailParam();
            pm.setFrom(mailParam.getFasongren());
            pm.setFromName(mailParam.getFasongrenName());
            pm.setTo(mailParam.getShoujianren());
            pm.setCc(mailParam.getChaosongren());
            pm.setSubject(mailParam.getYoujianzhuti());

            Context context = new Context();
            context.setVariable("lisUser",mailParam.getLisuser());

            String content = templateEngine.process("mailtemplate",context);

            pm.setContent(content);

            mailService.sendHtmlMail(pm);
            return new RestResult(ResultStatus.OK,"发送成功");
        }
        catch (Exception ex)
        {
            return new RestResult(ResultStatus.NG,"发送失败!原因："+ex.getMessage());
        }
    }


}
