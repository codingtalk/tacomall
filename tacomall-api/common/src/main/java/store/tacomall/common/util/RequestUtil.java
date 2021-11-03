/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-06 14:57:12
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @Just do what I think it is right
 */

package store.tacomall.common.util;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil extends JSONObject {

  private static final long serialVersionUID = 1L;

  public static JSONObject getLoginUser() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return (JSONObject) JSONObject.toJSON(request.getAttribute("LOGIN_USER_KEY"));
  }

}
