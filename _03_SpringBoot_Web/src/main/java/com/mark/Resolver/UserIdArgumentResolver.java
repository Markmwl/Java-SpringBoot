package com.mark.Resolver;

import com.mark.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@Component
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    //判断方法参数使用能使用当前的参数解析器进行解析
    public boolean supportsParameter(MethodParameter parameter) {
        //如果方法参数有叫上 CurrentUserId 注解，就能被我们的解析器解析
        return parameter.hasParameterAnnotation(CurrentUserId.class);
    }

    //进行参数解析的方法，可以在方法中获取对应的数据，然后把数据作为返回值返回。方法的返回值就会赋值给对应的方法参数
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //获取请求头中的token
        Map<String, String[]> map = webRequest.getParameterMap();
        if (map.size() > 0)
        {
            String token = map.get("userKey")[0];
            //解析token，获取userid
            Claims claims = JwtUtil.parseJWT(token);
            String userid = claims.getSubject();
            //返回结果
            return userid;
        }
        //返回结果
        return null;
    }
}
