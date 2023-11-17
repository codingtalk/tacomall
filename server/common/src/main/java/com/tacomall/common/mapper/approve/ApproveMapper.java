package com.tacomall.common.mapper.approve;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tacomall.common.entity.approve.Approve;
import com.tacomall.common.vo.base.PaginatorVo;


@Repository
public interface ApproveMapper extends BaseMapper<Approve> {
    
    Approve queryInfo(@Param(Constants.WRAPPER) Wrapper<Approve> wrapper);

    IPage<PaginatorVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<Approve> wrapper, Map<String, Object> qo);
}
