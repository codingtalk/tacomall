package com.tacomall.common.mapper.form;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.vo.base.PaginatorVo;

@Repository
public interface FormOrderMapper extends BaseMapper<FormOrder> {

    FormOrder queryInfo(@Param(Constants.WRAPPER) Wrapper<FormOrder> wrapper);

    IPage<PaginatorVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<FormOrder> wrapper);
}
