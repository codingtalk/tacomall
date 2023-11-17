package com.tacomall.common.mapper.member;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.tacomall.common.entity.member.Member;
import com.tacomall.common.vo.base.PaginatorVo;

@Repository
public interface MemberMapper extends BaseMapper<Member> {
  
    Member queryInfo(@Param(Constants.WRAPPER) Wrapper<Member> wrapper);

    IPage<PaginatorVo> queryPage(@Param("page") Page<?> page, @Param(Constants.WRAPPER) Wrapper<Member> wrapper);
}
