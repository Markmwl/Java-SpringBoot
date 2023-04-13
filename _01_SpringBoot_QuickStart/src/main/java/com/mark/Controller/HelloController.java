package com.mark.Controller;

import com.mark.Models.School;
import com.mark.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//@Controller
//@ResponseBody //规定只返回给响应体。做个标识，不让其当作页面跳转
@RestController //相当于 @Controller + @ResponseBody
public class HelloController {

    @Value("${name}")
    private String name;

    @Value("${student.age}")
    private Integer age;

    @Value("${date}")
    private Date date;

    @Autowired
    private Student student;

    @Autowired
    private School school;

    @RequestMapping("/hello")
    public String GetHello()
    {
        return "hello world!";
    }

    @RequestMapping("/name")
    public String GetName()
    {
        System.out.println(name+age+date);
        System.out.println(student);
        return name+age+date+student;
    }

    @RequestMapping("/school")
    public School GetSchool()
    {
        System.out.println(school);
        return school;
    }
}
