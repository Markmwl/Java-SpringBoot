package com.mark.Controller.NullZero;

import com.mark.Common.Result.RestResult;
import com.mark.Models.NullZero;
import com.mark.Service.NullZero.NullZeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/NullZero")
public class NullZeroController {
    @Autowired
    private NullZeroService nullZeroService;

    @PostMapping("/GetNullZero")
    public RestResult Login()
    {
        List<NullZero> nullZeros = nullZeroService.SelectNullZero();
        return new RestResult(1,"查询NullZero成功",nullZeros);
    }
}
