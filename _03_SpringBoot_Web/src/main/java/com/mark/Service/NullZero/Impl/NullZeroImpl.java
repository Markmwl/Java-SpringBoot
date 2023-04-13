package com.mark.Service.NullZero.Impl;

import com.mark.Aop.ModelsZeroMethod;
import com.mark.Models.NullZero;
import com.mark.Mybatis.NullZero.NullZeroMapper;
import com.mark.Service.NullZero.NullZeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NullZeroImpl implements NullZeroService {
    @Autowired
    private NullZeroMapper nullZeroMapper;

    @ModelsZeroMethod
    public List<NullZero> SelectNullZero()
    {
       return nullZeroMapper.SelectNullZero();
    }
}
