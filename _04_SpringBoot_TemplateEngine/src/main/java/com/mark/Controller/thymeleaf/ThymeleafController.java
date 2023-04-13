package com.mark.Controller.thymeleaf;

import com.mark.Models.User;
import com.mark.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ThymeleafController {

    @Autowired
    private UserService userService;

    @RequestMapping("/thymeleaf/users")
    public String users(Model model)
    {
        //获取数据
        List<User> users = userService.GetUserAll();
        //往域中存入数据
        model.addAttribute("code",1);
        model.addAttribute("message","hello thymeleaf!");
        model.addAttribute("users",users);
        //页面跳转
        return "table-standard";
    }
}
