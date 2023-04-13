package com.mark.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息模型")
public class User {
    @ApiModelProperty(value = "用户ID")
    public String id;
    @ApiModelProperty("用户姓名")
    public String name;
    @ApiModelProperty("用户性别")
    public String sex;
    @ApiModelProperty("用户年龄")
    public String age;
    @ApiModelProperty("用户地址")
    public String address;
    @ApiModelProperty("用户电话号码")
    public String phonenumber;
}
