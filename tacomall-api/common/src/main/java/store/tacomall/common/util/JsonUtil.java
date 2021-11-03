/***
 * @Author: 码上talk|RC
 * @Date: 2020-10-19 17:42:04
 * @LastEditTime: 2021-10-06 14:56:36
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/util/JsonUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
  private static final ObjectMapper JSON = new ObjectMapper();

  static {
    JSON.setSerializationInclusion(Include.NON_NULL);
    JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
  }

  public static String toJson(Object obj) {
    try {
      return JSON.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return null;
  }
}