package com.mark.Service.User;

import com.mark.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户信息
     * @return List<User>
     */
    List<User> GetUserAll();

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return User
     */
    User GetUserByID(Integer id);

    /**
     * 添加用户信息
     * @param user 要添加用户信息
     * @return true:执行成功 false：执行失败
     */
    boolean UserAdd(User user);

    /**
     * 修改用户信息
     * @param user 要修改用户信息
     * @return true:执行成功 false：执行失败
     */
    boolean UserUpd(User user);

    /**
     * 删除用户信息
     * @param ids 要删除用户的id集合
     * @return true:执行成功 false：执行失败
     */
    boolean UserDel(String[] ids);

    /**
     * 测试添加多条记录事务
     */
    void testUserAdds();
}
