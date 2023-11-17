package com.tacomall.apiopen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.sys.SysApp;
import com.tacomall.common.json.ResponseJson;

public interface SysAppService extends IService<SysApp> {

    ResponseJson<SysApp> info();
}
