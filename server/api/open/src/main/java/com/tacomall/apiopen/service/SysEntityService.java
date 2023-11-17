package com.tacomall.apiopen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.sys.SysEntity;
import com.tacomall.common.json.ResponseJson;

public interface SysEntityService extends IService<SysEntity> {

    ResponseJson<SysEntity> info(String domain);
}
