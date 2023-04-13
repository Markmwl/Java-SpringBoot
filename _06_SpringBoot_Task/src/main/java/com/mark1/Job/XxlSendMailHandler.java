package com.mark1.Job;

import com.alibaba.nacos.common.model.RestResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mark1.Models.BaiduHotSpot;
import com.mark1.Models.MailFileParam;
import com.mark1.Models.MailHotSpotParam;
import com.mark1.Models.MailParam;
import com.mark1.Service.EMail.MailService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class XxlSendMailHandler {
    private final static Logger logger = LoggerFactory.getLogger(XxlSendMailHandler.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @XxlJob("SendMailDByBaiduHotSpot")
    public void SendMailDByBaiduHotSpot() throws Exception {
        String MailData = XxlJobHelper.getJobParam();
        logger.info("接收调度中心参数...[{}]",MailData);

        URL url = new URL("https://api.1314.cool/getbaiduhot/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setConnectTimeout(60 * 1000);
        con.setRequestProperty("Content-Type", "application/json");
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
        int i = 1;
        while (iterator.hasNext()) {
            JsonNode next = iterator.next();
            BaiduHotSpot baiduHotSpot = objectMapper.readValue(next.toString(), BaiduHotSpot.class);
            baiduHotSpot.setWord(i + "." + baiduHotSpot.getWord());
            i++;
            lisHotSpot.add(baiduHotSpot);
        }

        ObjectMapper xmlMapper = new XmlMapper();
        MailHotSpotParam mpm = xmlMapper.readValue(MailData, MailHotSpotParam.class);
        MailParam mailParam = new MailParam();
        mailParam.setCc(mpm.getChaosongren());
        mailParam.setTo(mpm.getShoujianren());
        mailParam.setFrom(mpm.getFajianren());
        mailParam.setFromName(mpm.getFajianrenName());
        mailParam.setSubject("百度实时热点信息推送");
        Context context = new Context();
        context.setVariable("lisHotSpot", lisHotSpot);
        String content = templateEngine.process("baiduhotspot", context);

        mailParam.setContent(content);
        mailService.sendHtmlMail(mailParam);
        logger.info("百度实时热点推送成功！");
    }
}
