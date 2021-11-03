/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-10 17:19:29
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/service/OssService.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen.service;

import store.tacomall.common.json.ResponseJson;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

public interface OssService {
  ResponseJson<Map<String, Object>> authorize(String dir, Integer insertDb);

  ResponseJson<Object> callback(HttpServletRequest request);
}
