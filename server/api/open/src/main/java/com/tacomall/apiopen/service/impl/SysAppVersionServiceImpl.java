package com.tacomall.apiopen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.apiopen.service.SysAppVersionService;
import com.tacomall.common.entity.sys.SysAppVersion;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.sys.SysAppVersionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysAppVersionServiceImpl extends ServiceImpl<SysAppVersionMapper, SysAppVersion>
        implements SysAppVersionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseJson<SysAppVersion> info(String type) {
        ResponseJson<SysAppVersion> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper.selectOne(new QueryWrapper<SysAppVersion>()
                .lambda()
                .eq(SysAppVersion::getType,
                        type)
                .eq(SysAppVersion::getIsEnd, 0)));
        responseJson.ok();
        return responseJson;
    }
}
