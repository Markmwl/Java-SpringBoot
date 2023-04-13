package com.mark.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("发送邮件HTML入参")
public class MailHtmlParam {
    @ApiModelProperty(value = "发件人",required = true)
    private String fasongren;//邮件发送者
    @ApiModelProperty(value = "发件人名称")
    private String fasongrenName;//发件人名称
    @ApiModelProperty(value = "收件人",required = true)
    private String shoujianren;//收件人
    @ApiModelProperty(value = "抄送人")
    private String chaosongren;//抄送人
    @ApiModelProperty(value = "邮件主题",required = true)
    private String youjianzhuti;//邮件主题
    @ApiModelProperty(value = "邮件正文用户信息集合",required = true)
    private List<User> lisuser;
}
