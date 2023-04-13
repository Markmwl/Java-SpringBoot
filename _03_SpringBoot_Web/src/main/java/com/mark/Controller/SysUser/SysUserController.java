package com.mark.Controller.SysUser;

import com.mark.Common.CurrentLogin.CurrentUser;
import com.mark.Common.Result.RestResult;
import com.mark.Models.SysUser;
import com.mark.Service.SysUser.SysUserService;
import com.mark.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sys_user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public RestResult Login(@RequestBody SysUser sysUser)
    {
        Map<String,Object> map;
        //校验用户名和密码是否正确
        SysUser login = sysUserService.login(sysUser);
        //如果正确，生成Token返回
        if (login != null)
        {
            //生成Token
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(),String.valueOf(login.getId()), null);
            map = new HashMap<>();
            map.put("token",token);
            //记录本地
            CurrentUser.UserID = login.getId();
            CurrentUser.UserName =login.getName();
            CurrentUser.Token = token;
            //返回token
            return new RestResult(1,"登录成功",map);
        }
        return new RestResult(-1,"登录失败，用户名或密码错误！");
    }

    @PostMapping("/logincheck")
    public RestResult LoginCheck(@RequestBody Map<String,Object> maps)
    {
        Claims parseJWT = null;
        try {
            String token = (String)maps.get("token");
            parseJWT = JwtUtil.parseJWT(token);
            if(parseJWT.getSubject().equals(String.valueOf(CurrentUser.UserID)) && token.equals(CurrentUser.Token))
            {
                return new RestResult(1,"校验登录成功");
            }
            return new RestResult(-1,"校验登录失败,token已过期或无效！");
        } catch (Exception e) {
            return new RestResult(-1,"校验登录失败,token已过期或无效！");
        }
    }
}
