package com.mark.Common.Result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code","message","data"})
public class RestResult<T> {
    /**
     * 状态码
     */
    private Integer Code;
    /**
     * 提示信息
     */
    private String  Message;
    /**
     * 响应数据
     */
    private  T Data;

    /**
     * 返回响应数据
     * @param code 状态码
     * @param message 提示信息
     */
    public RestResult(Integer code, String message) {
        Code = code;
        Message = message;
    }

    /**
     * 返回响应数据
     * @param code 状态码
     * @param message 提示信息
     * @param data 响应数据
     */
    public RestResult(Integer code, String message, T data) {
        Code = code;
        Message = message;
        Data = data;
    }
}
