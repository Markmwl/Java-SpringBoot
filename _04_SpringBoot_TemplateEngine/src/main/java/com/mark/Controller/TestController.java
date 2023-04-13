package com.mark.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String Test()
    {
        return "Test";
    }

    @RequestMapping("/getReqAndRsp")
    public String getReqAndRsp(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        System.out.println("getReqAndRsp");
        return "getReqAndRsp";
    }
}
