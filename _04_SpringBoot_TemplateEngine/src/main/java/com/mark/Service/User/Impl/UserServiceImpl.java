package com.mark.Service.User.Impl;

import com.mark.Models.User;
import com.mark.Mybatis.User.UserMapper;
import com.mark.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> GetUserAll()
    {
       return userMapper.selectUserAll();
    }

    public User GetUserByID(Integer id)
    {
        return userMapper.GetUserByID(id);
    }

    /**
     * 添加用户信息
     * @param user 要添加用户信息
     * @return true:执行成功 false：执行失败
     */
    @Transactional
    public boolean UserAdd(User user)
    {
        return userMapper.UserAdd(user);
    }

    /**
     * 修改用户信息
     * @param user 要修改用户信息
     * @return true:执行成功 false：执行失败
     */
    @Transactional
    public boolean UserUpd(User user)
    {
        return userMapper.UserUpd(user);
    }

    /**
     * 删除用户信息
     * @param ids 要删除用户的id集合
     * @return true:执行成功 false：执行失败
     */
    @Transactional
    public boolean UserDel(String[] ids)
    {
        return userMapper.UserDel(ids);
    }

    /**
     * 测试添加多条记录事务
     */
    @Transactional
    public void testUserAdds()
    {
        userMapper.UserAdd(new User("1","test7","男","25","北京","189076271"));
        System.out.println(1/0);
        userMapper.UserAdd(new User("1","test8","男","26","石家庄","188006278"));
        userMapper.UserAdd(new User("1","test9","男","27","天津","1875462782"));
    }
}
