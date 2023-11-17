package com.tacomall.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SqlMapper extends BaseMapper {

    @Update({ "${sql}" })
    Integer updateExecute(@Param("sql") String sql);
}
