package com.mark.Controller.User;

import com.mark.Common.Result.RestResult;
import com.mark.Common.Result.ResultStatus;
import com.mark.Models.User;
import com.mark.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/GetUserAll")
    public RestResult GetUserAll()
    {
        List<User> users = userService.GetUserAll();
        if (users == null)
        {
            return new RestResult(ResultStatus.NG,"查询失败");
        }
        return new RestResult(ResultStatus.OK,"查询成功",users);
    }

    @RequestMapping("/GetUserByID")
    public RestResult GetUserByID(String userID)
    {
        System.out.println(userID);
        User user = userService.GetUserByID(Integer.parseInt(userID));
        if (user == null)
        {
            return new RestResult(ResultStatus.NG,"查询失败");
        }
        return new RestResult(ResultStatus.OK,"查询成功",user);
    }

    @PostMapping("/SetUserAdd")
    public RestResult SetUserAdd(@RequestBody User user)
    {
        if (!userService.UserAdd(user))
        {
            return new RestResult(ResultStatus.NG,"录入失败");
        }
        return new RestResult(ResultStatus.OK,"录入成功");
    }

    @PutMapping("/SetUserUpd")
    public RestResult SetUserUpd(@RequestBody User user)
    {
        if (!userService.UserUpd(user))
        {
            return new RestResult(ResultStatus.NG,"更新失败");
        }
        return new RestResult(ResultStatus.OK,"更新成功");
    }

    @DeleteMapping("/SetUserDel")
    public RestResult SetUserDel(@RequestParam("ids")String[] ids)
    {
        if (!userService.UserDel(ids))
        {
            return new RestResult(ResultStatus.NG,"删除失败");
        }
        return new RestResult(ResultStatus.OK,"删除成功");
    }

    @RequestMapping("/testUserAdds")
    public RestResult testUserAdds()
    {
        userService.testUserAdds();
        return new RestResult(ResultStatus.OK,"添加成功");
    }
}
