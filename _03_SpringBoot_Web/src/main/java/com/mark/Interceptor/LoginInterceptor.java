package com.mark.Interceptor;

import com.mark.Common.CurrentLogin.CurrentUser;
import com.mark.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在Handler方法执行前会被调用
     *
     * @param request  当前请求对象
     * @param response 响应对象
     * @param handler  请求被处理的方法 handlerMethod ,相当于是真正能够处理请求的handler方法封装成的对象，对象中有这方法的相关信息
     * @return 返回值代表是否放行，如果为true则放行，如果为fasle则拦截，目标方法执行不到
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在进入这个拦截器之前，对跨域提供支持
        if (responseCors(response, request)) {
            return false;
        }
        //获取请求头token
        String token = request.getHeader("token");
        //判断token是否为空，为空代表未登录，提醒重新登陆（401）
        if (!StringUtils.hasText(token)) {
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("用户未登录，请重新登录！");
        }
        //校验token是否成功
        Claims parseJWT = null;
        try {
            parseJWT = JwtUtil.parseJWT(token);
            if (parseJWT.getSubject().equals(String.valueOf(CurrentUser.UserID)) && token.equals(CurrentUser.Token)) {
                return true;
            } else {
                //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                throw new RuntimeException("用户校验失败，请重新登录！");
            }
        } catch (Exception e) {
            //response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException("用户校验失败，请重新登录！");
        }
    }

    /**
     * 在进入这个拦截器之前, 对跨域提供支持
     * @param response
     * @param request
     * @return
     */
    private boolean responseCors(HttpServletResponse response, HttpServletRequest request) {
        // 判断是否是预检请求
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            // response.setHeader("Cache-Control","no-cache");
            response.setHeader("Access-control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
            response.setHeader("Access-Control-Allow-Headers", "token");
            // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        return false;
    }
}
