package com.mark1.MyBatis.xxl;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.mark1.Models.XxlUser;

import java.util.List;

@Mapper
@Repository
public interface xxlMapper {
    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    List<XxlUser> selectXXLUserAll();

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    XxlUser XXLGetUserByID(Integer id);

    /**
     * 添加用户信息
     * @param user 要添加用户信息
     * @return true:执行成功 false：执行失败
     */
    Boolean XXLUserAdd(XxlUser user);

    /**
     * 修改用户信息
     * @param user 要修改用户信息
     * @return true:执行成功 false：执行失败
     */
    Boolean XXLUserUpd(XxlUser user);

    /**
     * 删除用户信息
     * @param ids 要删除用户的id集合
     * @return true:执行成功 false：执行失败
     */
    Boolean XXLUserDel(@Param("ids")String[] ids);
}
