package com.mark1.Controller;

import com.alibaba.nacos.common.model.RestResult;
import com.mark1.Common.Result.ResultStatus;
import com.mark1.Models.XxlUser;
import com.mark1.Service.xxl.xxlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String Test()
    {
        return "Test";
    }

    @Autowired
    private xxlService service;

    @GetMapping("/GetUserAll")
    public RestResult GetUserAll()
    {
        List<XxlUser> users = service.XXLGetUserAll();
        if (users == null)
        {
            return new RestResult(ResultStatus.NG,"查询失败");
        }
        return new RestResult(ResultStatus.OK,"查询成功",users);
    }
}
