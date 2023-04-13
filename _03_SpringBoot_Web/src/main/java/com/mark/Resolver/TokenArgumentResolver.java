package com.mark.Resolver;

import com.mark.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.UUID;

import static com.mark.Utils.JwtUtil.createJWT;

@Component
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    //判断方法参数使用能使用当前的参数解析器进行解析
    public boolean supportsParameter(MethodParameter parameter) {
        //如果方法参数有叫上 CurrentUserId 注解，就能被我们的解析器解析
        return parameter.hasParameterAnnotation(CurrentToken.class);
    }

    //进行参数解析的方法，可以在方法中获取对应的数据，然后把数据作为返回值返回。方法的返回值就会赋值给对应的方法参数
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception
    {
        //获取请求头中的token
        String token = webRequest.getHeader("token");
        Map<String, String[]> map = webRequest.getParameterMap();
        if (StringUtils.hasText(token) || map.size() > 0)
        {
            String userID = map.get("userID")[0];
            String jwt = createJWT(UUID.randomUUID().toString(), userID, null,token);
            //返回结果
            return jwt;
        }
        //返回结果
        return null;
    }
}
