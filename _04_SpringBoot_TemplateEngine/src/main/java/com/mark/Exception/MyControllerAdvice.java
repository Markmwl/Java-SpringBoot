package com.mark.Exception;

import com.mark.Common.Result.RestResult;
import com.mark.Common.Result.ResultStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public RestResult handlerException(Exception ex) {
        return new RestResult(ResultStatus.ER,"执行失败！原因：" + ex.getMessage());
    }
}
