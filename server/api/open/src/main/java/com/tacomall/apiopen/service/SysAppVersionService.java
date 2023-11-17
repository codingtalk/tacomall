package com.tacomall.apiopen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.sys.SysAppVersion;
import com.tacomall.common.json.ResponseJson;

public interface SysAppVersionService extends IService<SysAppVersion> {

    ResponseJson<SysAppVersion> info(String type);
}
