package com.mark1.Service.xxl;

import com.mark1.Models.XxlUser;

import java.util.List;


public interface xxlService {
    /**
     * 查询所有用户信息
     * @return List<User>
     */
    List<XxlUser> XXLGetUserAll();

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return User
     */
    XxlUser XXLGetUserByID(Integer id);

    /**
     * 添加用户信息
     * @param user 要添加用户信息
     * @return true:执行成功 false：执行失败
     */
    boolean XXLUserAdd(XxlUser user);

    /**
     * 修改用户信息
     * @param user 要修改用户信息
     * @return true:执行成功 false：执行失败
     */
    boolean XXLUserUpd(XxlUser user);

    /**
     * 删除用户信息
     * @param ids 要删除用户的id集合
     * @return true:执行成功 false：执行失败
     */
    boolean XXLUserDel(String[] ids);

    /**
     * 测试添加多条记录事务
     */
    void testXXLUserAdds();
}
