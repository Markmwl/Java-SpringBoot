package com.mark.Service.SysUser.Impl;

import com.mark.Models.SysUser;
import com.mark.Mybatis.SysUser.SysUserMapper;
import com.mark.Service.SysUser.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser login(SysUser sysUser) {
        SysUser userLogin = sysUserMapper.UserLogin(sysUser);
        if (userLogin != null)
        {
            return userLogin;
        }
        return null;
    }
}
