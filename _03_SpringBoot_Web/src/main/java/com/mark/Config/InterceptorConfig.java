package com.mark.Config;

import com.mark.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)//添加拦截器
                .addPathPatterns("/**") //配置拦截路径
                .excludePathPatterns("/sys_user/**","/swagger-resources/**","/swagger-ui/**", "/v3/**", "/error","/swagger-ui/index.html","/swagger-ui/index.html/**"); //静态资源没在本项目中所以不需要排除，只排除登录相关接口即可！
    }
}
