package store.tacomall.common.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
  public static String getIpAddr(HttpServletRequest request) {
    String srtUnknown = "unknown";
    String ip = null;
    try {
      ip = request.getHeader("x-forwarded-for");
      if (StringUtil.isEmpty(ip) || srtUnknown.equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
      }
      if (StringUtil.isEmpty(ip) || ip.length() == 0 || srtUnknown.equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (StringUtil.isEmpty(ip) || srtUnknown.equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if (StringUtil.isEmpty(ip) || srtUnknown.equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (StringUtil.isEmpty(ip) || srtUnknown.equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
      }
    } catch (Exception e) {
    }

    return ip;
  }
}
