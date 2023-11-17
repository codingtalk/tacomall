package com.tacomall.apiopen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.apiopen.service.SysEntityService;
import com.tacomall.common.entity.sys.SysEntity;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.sys.SysEntityMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysEntityServiceImpl extends ServiceImpl<SysEntityMapper, SysEntity> implements SysEntityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseJson<SysEntity> info(String domain) {
        ResponseJson<SysEntity> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper.selectOne(new QueryWrapper<SysEntity>()
                .lambda()
                .eq(SysEntity::getDomain,
                        domain)));
        responseJson.ok();
        return responseJson;
    }
}
