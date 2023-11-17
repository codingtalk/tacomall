
package com.tacomall.apiopen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tacomall.apiopen.service.SysAppService;
import com.tacomall.apiopen.service.SysAppVersionService;
import com.tacomall.apiopen.service.SysEntityService;
import com.tacomall.common.entity.sys.SysApp;
import com.tacomall.common.entity.sys.SysAppVersion;
import com.tacomall.common.entity.sys.SysEntity;
import com.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/sys")
public class SysController {

    @Autowired
    SysEntityService sysEntityService;

    @Autowired
    SysAppService sysAppService;

    @Autowired
    SysAppVersionService sysAppVersionService;

    @PostMapping("entity")
    public ResponseJson<SysEntity> entity(@RequestParam(value = "domain") String domain) {
        return sysEntityService.info(domain);
    }

    @PostMapping("app")
    public ResponseJson<SysApp> client() {
        return sysAppService.info();
    }

    @PostMapping("appVersion")
    public ResponseJson<SysAppVersion> clientVersion(@RequestParam(value = "type") String type) {
        return sysAppVersionService.info(type);
    }

}
