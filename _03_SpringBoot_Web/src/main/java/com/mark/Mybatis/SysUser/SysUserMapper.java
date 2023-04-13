package com.mark.Mybatis.SysUser;

import com.mark.Models.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserMapper {

    SysUser UserLogin(SysUser sysUser);
}
