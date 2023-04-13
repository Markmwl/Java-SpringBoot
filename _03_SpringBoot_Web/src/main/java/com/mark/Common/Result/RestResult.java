package com.mark.Common.Result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code","message","data"})
@ApiModel("响应报文")
public class RestResult<T> {
    /**
     * 状态码
     */
    @ApiModelProperty("响应状态码")
    private Integer code;
    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String  message;
    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private  T data;

    /**
     * 返回响应数据
     * @param _code 状态码
     * @param _message 提示信息
     */
    public RestResult(Integer _code, String _message) {
        code = _code;
        message = _message;
    }

    /**
     * 返回响应数据
     * @param _code 状态码
     * @param _message 提示信息
     * @param _data 响应数据
     */
    public RestResult(Integer _code, @ApiParam("提示信息")String _message, T _data) {
        code = _code;
        message = _message;
        data = _data;
    }
}
