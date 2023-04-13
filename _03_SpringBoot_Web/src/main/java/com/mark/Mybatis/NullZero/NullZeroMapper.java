package com.mark.Mybatis.NullZero;

import com.mark.Models.NullZero;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NullZeroMapper {
    List<NullZero> SelectNullZero();
}
