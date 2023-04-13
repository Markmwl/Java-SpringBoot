package com.mark.Controller.Email;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.Common.Result.RestResult;
import com.mark.Common.Result.ResultStatus;
import com.mark.Models.BaiduHotSpot;
import com.mark.Models.MailHotSpotParam;
import com.mark.Models.MailHtmlParam;
import com.mark.Models.MailParam;
import com.mark.Service.EMail.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@Api(tags = "发送实时热点信息")
public class EmailHotSpotController {
    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @ApiOperation("获取百度实时热点信息")
    @GetMapping("/GetBaiduHotSpot")
    public RestResult GetBaiduHotSpot()
    {
        try{
            URL url = new URL("https://api.1314.cool/getbaiduhot/");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.setConnectTimeout(60*1000);
            con.setRequestProperty("Content-Type","application/json");
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String data = br.readLine();
            br.close();
            is.close();
            con.disconnect();

            ObjectMapper objectMapper = new ObjectMapper();
            List<BaiduHotSpot> lisHotSpot = new ArrayList<>();
            JsonNode jsonNode = objectMapper.readTree(data);
            JsonNode datajson = jsonNode.get("data");
            Iterator<JsonNode> iterator = datajson.iterator();
            int i =1;
            while (iterator.hasNext()){
                JsonNode next = iterator.next();
                BaiduHotSpot baiduHotSpot = objectMapper.readValue(next.toString(),BaiduHotSpot.class);
                baiduHotSpot.setWord(i +"."+ baiduHotSpot.getWord());
                i++;
                lisHotSpot.add(baiduHotSpot);
            }

            return new RestResult(ResultStatus.OK,"获取成功",lisHotSpot);
        }
        catch (Exception ex)
        {
            return new RestResult(ResultStatus.NG,"发送失败!原因："+ex.getMessage());
        }
    }

    @ApiOperation("发送实时热点信息")
    @GetMapping("/SendMailHotSpot")
    public RestResult SendMailHotSpot(@Validated MailHotSpotParam mpm) {
        try{
            MailParam mailParam = new MailParam();
            mailParam.setCc(mpm.getCc());
            mailParam.setTo(mpm.getTo());
            mailParam.setFrom(mpm.getFrom());
            mailParam.setFromName(mpm.getFromName());
            mailParam.setSubject("实时热点信息");
            RestResult restResult = GetBaiduHotSpot();
            List<BaiduHotSpot> lisHotSpot= new ArrayList<>();
            if (restResult.getCode() == ResultStatus.OK)
            {
                lisHotSpot= (List<BaiduHotSpot>)restResult.getData();
            }
            Context context = new Context();
            context.setVariable("lisHotSpot",lisHotSpot);
            String content = templateEngine.process("baiduhotspot",context);

            mailParam.setContent(content);
            mailService.sendHtmlMail(mailParam);
            return new RestResult(ResultStatus.OK,"发送成功");
        }
        catch (Exception ex)
        {
            return new RestResult(ResultStatus.NG,"发送失败!原因："+ex.getMessage());
        }
    }
}
