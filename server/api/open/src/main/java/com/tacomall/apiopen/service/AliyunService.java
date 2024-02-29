
package com.tacomall.apiopen.service;

import jakarta.servlet.http.HttpServletRequest;

import com.tacomall.common.json.ResponseJson;

import java.util.Map;

public interface AliyunService {

    ResponseJson<Map<String, Object>> OSSAuthorize(String dir, Integer insertDb);

    ResponseJson<Object> OSSUploadNotify(HttpServletRequest request);

    ResponseJson<String> SMSSend(String app, Integer bizType, String mobile) throws Exception;
}
