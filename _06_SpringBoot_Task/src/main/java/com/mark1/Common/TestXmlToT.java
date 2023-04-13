package com.mark1.Common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mark1.Models.MailHotSpotParam;

public class TestXmlToT {


    public static void main(String[] args)  throws Exception {
        ObjectMapper xmlMapper = new XmlMapper();
        MailHotSpotParam mailHotSpotParam = new MailHotSpotParam();
        mailHotSpotParam.setChaosongren(null);
        mailHotSpotParam.setFajianren("528414865@qq.com");
        mailHotSpotParam.setShoujianren("528414865@qq.com");
        String value = xmlMapper.writeValueAsString(mailHotSpotParam);
        System.out.println(value);

        String xmlvalue ="<MailHotSpotParam>\n" +
                "  <fajianren>528414865@qq.com</fajianren>\n" +
                "  <shoujianren>528414865@qq.com</shoujianren>\n" +
                "  <chaosongren>null</chaosongren>\n" +
                "</MailHotSpotParam>";
        MailHotSpotParam mailHotSpotParam1 = xmlMapper.readValue(xmlvalue, MailHotSpotParam.class);
        System.out.println(mailHotSpotParam1.toString());
    }
}
