package com.mark.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("发送邮件入参")
public class MailFileParam {
    @ApiModelProperty(value = "发件人",required = true)
    private String from;//邮件发送者
    @ApiModelProperty(value = "发件人名称")
    private String fromName;//发件人人名称
    @ApiModelProperty(value = "收件人",required = true)
    private String to;//收件人
    @ApiModelProperty(value = "抄送人")
    private String cc;//抄送人
    @ApiModelProperty(value = "邮件主题",required = true)
    private String subject;//邮件主题
    @ApiModelProperty(value = "邮件内容",required = true)
    private String content;//邮件内容
    @ApiModelProperty(value = "附件-文件路径",required = true)
    private String file; //附件
}
