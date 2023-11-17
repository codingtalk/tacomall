package com.tacomall.common.mapper.approve;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tacomall.common.entity.approve.ApproveType;
import com.tacomall.common.vo.base.PaginatorVo;

@Repository
public interface ApproveTypeMapper extends BaseMapper<ApproveType> {

    ApproveType queryInfo(@Param(Constants.WRAPPER) Wrapper<ApproveType> wrapper);

    Page<PaginatorVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<ApproveType> wrapper);

}
