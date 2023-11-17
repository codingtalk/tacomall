package com.tacomall.apiopen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.apiopen.service.SysAppService;
import com.tacomall.common.entity.sys.SysApp;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.sys.SysAppMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysAppServiceImpl extends ServiceImpl<SysAppMapper, SysApp> implements SysAppService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseJson<SysApp> info() {
        ResponseJson<SysApp> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper.selectOne(null));
        responseJson.ok();
        return responseJson;
    }
}
