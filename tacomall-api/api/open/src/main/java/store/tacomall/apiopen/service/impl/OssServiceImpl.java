/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-10 21:04:17
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/service/impl/OssServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import store.tacomall.common.config.OssConfig;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.apiopen.service.OssService;

@Service
public class OssServiceImpl implements OssService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public ResponseJson<Map<String, Object>> authorize(String dir, Integer insertDb) {
    ResponseJson<Map<String, Object>> responseJson = new ResponseJson<>();
    Map<String, Object> map = new HashMap<>();
    try {
      OSSClient client = OssConfig.getOSSClient();

      PolicyConditions policyConds = new PolicyConditions();
      policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
      policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

      long expireEndTime = System.currentTimeMillis() + OssConfig.expire * 1000;
      Date expiration = new Date(expireEndTime);
      String postPolicy = client.generatePostPolicy(expiration, policyConds);
      byte[] binaryData = postPolicy.getBytes("utf-8");
      String encodedPolicy = BinaryUtil.toBase64String(binaryData);
      String postSignature = client.calculatePostSignature(postPolicy);
      JSONObject jasonCallback = new JSONObject();
      jasonCallback.put("callbackUrl", OssConfig.callback);
      jasonCallback.put("callbackBody", "bucket=" + OssConfig.bucket + "&insertDb=" + insertDb
          + "&filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
      jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
      String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
      map.put("accessKey", OssConfig.accessKey);
      map.put("dir", dir);
      map.put("expire", OssConfig.expire);
      map.put("host", OssConfig.host);
      map.put("policy", encodedPolicy);
      map.put("signature", postSignature);
      map.put("callback", base64CallbackBody);
    } catch (Exception e) {
      this.logger.info("Storage authorize fail");
    }
    responseJson.setData(map);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<Object> callback(HttpServletRequest request) {
    ResponseJson<Object> responseJson = new ResponseJson<>();
    Map<String, String> ossMap = new HashMap<>();
    ossMap.put("filename", request.getParameter("filename"));
    ossMap.put("bucket", request.getParameter("bucket"));
    ossMap.put("url", "//" + OssConfig.host + "/" + request.getParameter("filename"));
    responseJson.setData(ossMap);
    responseJson.ok();
    return responseJson;
  }
}
