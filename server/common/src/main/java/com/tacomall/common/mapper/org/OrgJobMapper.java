package com.tacomall.common.mapper.org;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tacomall.common.entity.org.OrgJob;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgJobMapper extends BaseMapper<OrgJob> {

    @Select("SELECT * FROM org_job ${ew.customSqlSegment}")
    IPage<OrgJob> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<OrgJob> wrapper);

}
