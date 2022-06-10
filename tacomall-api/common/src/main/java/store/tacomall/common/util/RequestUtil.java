/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-06 14:57:12
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @Just do what I think it is right
 */

package store.tacomall.common.util;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.util.ObjectUtil;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil extends JSONObject {

  private static final long serialVersionUID = 1L;

  public static JSONObject getLoginUser() {
    return (JSONObject) JSONObject
        .toJSON((((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest())
            .getAttribute("LOGIN_USER_KEY"));
  }

  public static Integer getIntegerParam(String name) {
    String param = (((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest())
        .getParameter(name);
    if (ObjectUtil.isNull(param)) {
      ExceptionUtil.throwClientException("参数错误");
    }
    return Integer.valueOf(param);
  }

  public static String getStringParam(String name) {
    String param = (((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest())
        .getParameter(name);
    if (ObjectUtil.isNull(param)) {
      ExceptionUtil.throwClientException("参数错误");
    }
    return param;
  }

}
