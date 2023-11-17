package com.tacomall.common.mapper.org;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tacomall.common.entity.org.OrgAccessRule;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgAccessRuleMapper extends BaseMapper<OrgAccessRule> {

    @Select("SELECT * FROM org_access_rule ${ew.customSqlSegment}")
    IPage<OrgAccessRule> queryPage(@Param("page") Page<?> page,
            @Param(Constants.WRAPPER) Wrapper<OrgAccessRule> wrapper);

}
