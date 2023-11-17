package com.tacomall.common.mapper.org;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tacomall.common.entity.org.OrgDept;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgDeptMapper extends BaseMapper<OrgDept> {

    @Select("SELECT * FROM org_dept ${ew.customSqlSegment}")
    IPage<OrgDept> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<OrgDept> wrapper);

    List<OrgDept> queryListByMap(@Param("map") Map<String, Object> map);
}
