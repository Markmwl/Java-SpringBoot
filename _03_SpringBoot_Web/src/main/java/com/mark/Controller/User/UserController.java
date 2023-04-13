package com.mark.Controller.User;

import com.mark.Common.Result.RestResult;
import com.mark.Common.Result.ResultStatus;
import com.mark.Models.User;
import com.mark.Resolver.CurrentToken;
import com.mark.Resolver.CurrentUserId;
import com.mark.Service.User.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.mark.Utils.JwtUtil.createJWT;

@RestController
//@CrossOrigin
@Api(tags = "获取用户信息模块")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户数据")
    @GetMapping("/GetUserAll")
    public RestResult GetUserAll()
    {
        List<User> users = userService.GetUserAll();
        if (users == null)
        {
            return new RestResult(ResultStatus.NG,"查询失败");
        }
        return new RestResult(ResultStatus.OK,"查询成功",users);
    }

    @ApiOperation("根据用户ID返回加密key")
    @GetMapping("/SetUserIDKey")
    public RestResult SetUserIDKey(@ApiParam("用户ID(需要加密的用户id)") @CurrentToken String userID)
    {
        System.out.println(userID);
        return new RestResult(ResultStatus.OK,"查询成功",userID);
    }

    @ApiOperation("根据加密key获取用户数据")
    @GetMapping("/GetUserBykey")
    public RestResult GetUserBykey(@ApiParam("用户ID(已鉴权，需要用户Key才能访问)")@CurrentUserId String userKey)
    {
        User user = userService.GetUserByID(Integer.parseInt(userKey));
        if (user == null)
        {
            return new RestResult(ResultStatus.NG,"查询失败");
        }
        return new RestResult(ResultStatus.OK,"查询成功",user);
    }


    @ApiOperation("根据用户ID获取用户数据")
    @GetMapping("/GetUserByID")
    public RestResult GetUserByID(@ApiParam("用户ID") String userID)
    {
        System.out.println(userID);
        User user = userService.GetUserByID(Integer.parseInt(userID));
        if (user == null)
        {
            return new RestResult(ResultStatus.NG,"查询失败");
        }
        return new RestResult(ResultStatus.OK,"查询成功",user);
    }

    @ApiOperation("添加用户")
    @PostMapping("/SetUserAdd")
    public RestResult SetUserAdd(@RequestBody User user)
    {
        if (!userService.UserAdd(user))
        {
            return new RestResult(ResultStatus.NG,"录入失败");
        }
        return new RestResult(ResultStatus.OK,"录入成功");
    }

    @ApiOperation("根据用户ID更新用户数据")
    @PutMapping("/SetUserUpd")
    public RestResult SetUserUpd(@RequestBody User user)
    {
        if (!userService.UserUpd(user))
        {
            return new RestResult(ResultStatus.NG,"更新失败");
        }
        return new RestResult(ResultStatus.OK,"更新成功");
    }

    @ApiOperation("根据用户ID集合删除用户信息")
    @DeleteMapping("/SetUserDel")
    public RestResult SetUserDel(@ApiParam("用户ID集合")@RequestParam("ids")String[] ids)
    {
        if (!userService.UserDel(ids))
        {
            return new RestResult(ResultStatus.NG,"删除失败");
        }
        return new RestResult(ResultStatus.OK,"删除成功");
    }

    @ApiOperation("添加默认用户数据")
    @PostMapping("/testUserAdds")
    public RestResult testUserAdds()
    {
        userService.testUserAdds();
        return new RestResult(ResultStatus.OK,"添加成功");
    }
}
