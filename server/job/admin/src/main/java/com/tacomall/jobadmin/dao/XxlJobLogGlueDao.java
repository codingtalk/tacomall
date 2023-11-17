package com.tacomall.jobadmin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tacomall.jobadmin.core.model.XxlJobLogGlue;

import java.util.List;

/**
 * job log for glue
 * 
 * @author xuxueli 2016-5-19 18:04:56
 */
@Mapper
public interface XxlJobLogGlueDao {

    public int save(XxlJobLogGlue xxlJobLogGlue);

    public List<XxlJobLogGlue> findByJobId(@Param("jobId") int jobId);

    public int removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

    public int deleteByJobId(@Param("jobId") int jobId);

}
