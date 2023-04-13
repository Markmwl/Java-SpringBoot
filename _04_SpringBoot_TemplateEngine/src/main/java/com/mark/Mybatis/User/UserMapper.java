package com.mark.Mybatis.User;

import com.mark.Models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository //注入容器，其实mybais已经帮我们注入，加此是因为idea没找到UserMapperr对应的sprin容器，所以在调用时会爆红，加此不会爆红，加不加都不影响使用
public interface UserMapper {

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    List<User> selectUserAll();

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    User GetUserByID(Integer id);

    /**
     * 添加用户信息
     * @param user 要添加用户信息
     * @return true:执行成功 false：执行失败
     */
    Boolean UserAdd(User user);

    /**
     * 修改用户信息
     * @param user 要修改用户信息
     * @return true:执行成功 false：执行失败
     */
    Boolean UserUpd(User user);

    /**
     * 删除用户信息
     * @param ids 要删除用户的id集合
     * @return true:执行成功 false：执行失败
     */
    Boolean UserDel(@Param("ids") String[] ids);
}
