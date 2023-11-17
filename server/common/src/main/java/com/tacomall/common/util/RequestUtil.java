package com.tacomall.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.util.ObjectUtil;

import org.apache.commons.codec.binary.Hex;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil extends JSONObject {

    private static final long serialVersionUID = 1L;

    public static JSONObject getLoginUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return (JSONObject) JSONObject.toJSON(request.getAttribute("LOGIN_USER_KEY"));
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            byte[] bytes = new byte[2048];
            inputStream.read(bytes);
            System.out.print(Hex.encodeHexString(bytes));
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.print("================ line =============>: " + line + "\n");
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static String getBodyHexStr(ServletRequest request) {
        String s = "";
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            byte[] bytes = new byte[150];
            inputStream.read(bytes);
            s = Hex.encodeHexString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return s;
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
