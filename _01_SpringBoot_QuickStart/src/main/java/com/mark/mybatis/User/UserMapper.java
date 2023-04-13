package com.mark.mybatis.User;

import com.mark.Models.User1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository //注入容器，其实mybais已经帮我们注入，加此是因为idea没找到UserMapperr对应的sprin容器，所以在调用时会爆红，加此不会爆红，加不加都不影响使用
public interface UserMapper {

    List<User1> selectUserAll();

    User1 GetUserByID(Integer id);
}
