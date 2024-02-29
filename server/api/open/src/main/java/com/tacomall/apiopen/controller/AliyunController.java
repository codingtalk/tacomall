
package com.tacomall.apiopen.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tacomall.apiopen.service.AliyunService;
import com.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/aliyun")
public class AliyunController {

    @Autowired
    AliyunService aliyunService;

    @PostMapping("ossAuthorize")
    public ResponseJson<Map<String, Object>> ossAuthorize(@RequestParam(value = "dir", defaultValue = "") String dir,
            @RequestParam(value = "insertDb", defaultValue = "0") Integer insertDb) {
        return aliyunService.OSSAuthorize(dir, insertDb);
    }

    @PostMapping("verifyCode")
    public ResponseJson<String> verifyCode(@RequestParam(value = "app") String app,
            @RequestParam(value = "bizType") Integer bizType, @RequestParam(value = "mobile") String mobile)
            throws Exception {
        return aliyunService.SMSSend(app, bizType, mobile);
    }

}
