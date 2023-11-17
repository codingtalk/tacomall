package com.tacomall.common.mapper.org;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tacomall.common.entity.org.OrgDept;
import com.tacomall.common.entity.org.OrgStaff;

@Repository
public interface OrgStaffMapper extends BaseMapper<OrgStaff> {

    OrgStaff queryInfo(@Param(Constants.WRAPPER) Wrapper<OrgStaff> wrapper);

    IPage<OrgStaff> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<OrgStaff> wrapper);

    List<OrgDept> queryListByMap(@Param("map") Map<String, Object> map);
}
