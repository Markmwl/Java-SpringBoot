package com.mark1.Service.xxl.Impl;

import com.mark1.Models.XxlUser;
import com.mark1.MyBatis.xxl.xxlMapper;
import com.mark1.Service.xxl.xxlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class xxlImpl implements xxlService {
    @Autowired
    private xxlMapper userMapper;

    public List<XxlUser> XXLGetUserAll()
    {
        return userMapper.selectXXLUserAll();
    }

    public XxlUser XXLGetUserByID(Integer id)
    {
        return userMapper.XXLGetUserByID(id);
    }

    /**
     * 添加用户信息
     * @param user 要添加用户信息
     * @return true:执行成功 false：执行失败
     */
    @Transactional
    public boolean XXLUserAdd(XxlUser user)
    {
        return userMapper.XXLUserAdd(user);
    }

    /**
     * 修改用户信息
     * @param user 要修改用户信息
     * @return true:执行成功 false：执行失败
     */
    @Transactional
    public boolean XXLUserUpd(XxlUser user)
    {
        return userMapper.XXLUserUpd(user);
    }

    /**
     * 删除用户信息
     * @param ids 要删除用户的id集合
     * @return true:执行成功 false：执行失败
     */
    @Transactional
    public boolean XXLUserDel(String[] ids)
    {
        return userMapper.XXLUserDel(ids);
    }

    /**
     * 测试添加多条记录事务
     */
    @Transactional
    public void testXXLUserAdds()
    {
        userMapper.XXLUserAdd(new XxlUser("1","test7","男","25","北京","189076271","sysdate"));
        System.out.println(1/0);
        userMapper.XXLUserAdd(new XxlUser("1","test8","男","26","石家庄","188006278","sysdate"));
        userMapper.XXLUserAdd(new XxlUser("1","test9","男","27","天津","1875462782","sysdate"));
    }
}

