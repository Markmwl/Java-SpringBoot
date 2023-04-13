package com.mark.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticController {

    @RequestMapping("/hello")
    public String Hello()
    {
        return "hello static!";
    }
}
