/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-06 14:57:19
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/util/HttpRequestUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import java.util.Map;
import java.util.Collections;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpRequestUtil {

  private RestTemplate restTemplate;
  private String baseUrl;

  public HttpRequestUtil(RestTemplate request, String url) {
    restTemplate = request;
    baseUrl = url;
  }

  public JSONObject post(String path, Map<String, Object> params) {
    JSONObject json = new JSONObject();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    HttpEntity httpEntity = new HttpEntity(params, headers);
    try {
      ResponseEntity<JSONObject> request = restTemplate.postForEntity(baseUrl + path, httpEntity, JSONObject.class);
      json = request.getBody();
    } catch (Exception ex) {
      ExceptionUtil.throwServerException("http错误");
    }
    return json;
  }
}
