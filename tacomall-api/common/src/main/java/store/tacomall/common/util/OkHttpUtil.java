/***
 * @Author: 码上talk|RC
 * @Date: 2021-09-18 14:01:11
 * @LastEditTime: 2021-10-06 14:56:58
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/util/OkHttpUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
  private static volatile OkHttpClient okHttpClient = null;
  private static volatile Semaphore semaphore = null;
  private Map<String, String> headerMap;
  private Map<String, String> paramMap;
  private String url;
  private Request.Builder request;

  /**
   * 初始化okHttpClient，并且允许https访问
   */
  private OkHttpUtil() {
    if (okHttpClient == null) {
      synchronized (OkHttpUtil.class) {
        if (okHttpClient == null) {
          TrustManager[] trustManagers = buildTrustManagers();
          okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
              .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
              .sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0])
              .hostnameVerifier((hostName, session) -> true).retryOnConnectionFailure(true).build();
          addHeader("User-Agent",
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        }
      }
    }
  }

  /**
   * 用于异步请求时，控制访问线程数，返回结果
   *
   * @return
   */
  private static Semaphore getSemaphoreInstance() {
    // 只能1个线程同时访问
    synchronized (OkHttpUtil.class) {
      if (semaphore == null) {
        semaphore = new Semaphore(0);
      }
    }
    return semaphore;
  }

  /**
   * 创建OkHttpUtil
   *
   * @return
   */
  public static OkHttpUtil builder() {
    return new OkHttpUtil();
  }

  /**
   * 添加url
   *
   * @param url
   * @return
   */
  public OkHttpUtil url(String url) {
    this.url = url;
    return this;
  }

  /**
   * 添加参数
   * 
   * @param key   参数名
   * @param value 参数值
   * @return
   */
  public OkHttpUtil addParam(String key, String value) {
    if (paramMap == null) {
      paramMap = new LinkedHashMap<>(16);
    }
    paramMap.put(key, value);
    return this;
  }

  /**
   * 添加请求头
   *
   * @param key   参数名
   * @param value 参数值
   * @return
   */
  public OkHttpUtil addHeader(String key, String value) {
    if (headerMap == null) {
      headerMap = new LinkedHashMap<>(16);
    }
    headerMap.put(key, value);
    return this;
  }

  /**
   * 初始化get方法
   *
   * @return
   */
  public OkHttpUtil get() {
    request = new Request.Builder().get();
    StringBuilder urlBuilder = new StringBuilder(url);
    if (paramMap != null) {
      urlBuilder.append("?");
      try {
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
          urlBuilder.append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=")
              .append(URLEncoder.encode(entry.getValue(), "utf-8")).append("&");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      urlBuilder.deleteCharAt(urlBuilder.length() - 1);
    }
    request.url(urlBuilder.toString());
    return this;
  }

  /**
   * 初始化post方法
   *
   * @param isJsonPost true等于json的方式提交数据，类似postman里post方法的raw false等于普通的表单提交
   * @return
   */
  public OkHttpUtil post(boolean isJsonPost) {
    RequestBody requestBody;
    if (isJsonPost) {
      String json = "";
      if (paramMap != null) {
        json = JSON.toJSONString(paramMap);
      }
      requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    } else {
      FormBody.Builder formBody = new FormBody.Builder();
      if (paramMap != null) {
        paramMap.forEach(formBody::add);
      }
      requestBody = formBody.build();
    }
    request = new Request.Builder().post(requestBody).url(url);
    return this;
  }

  /**
   * 同步请求
   *
   * @return
   */
  public String sync() {
    setHeader(request);
    try {
      Response response = okHttpClient.newCall(request.build()).execute();
      assert response.body() != null;
      return response.body().string();
    } catch (IOException e) {
      e.printStackTrace();
      return "请求失败：" + e.getMessage();
    }
  }

  /**
   * 异步请求，有返回值
   */
  public String async() {
    StringBuilder buffer = new StringBuilder("");
    setHeader(request);
    okHttpClient.newCall(request.build()).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        buffer.append("请求出错：").append(e.getMessage());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        assert response.body() != null;
        buffer.append(response.body().string());
        getSemaphoreInstance().release();
      }
    });
    try {
      getSemaphoreInstance().acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }

  /**
   * 异步请求，带有接口回调
   *
   * @param callBack
   */
  public void async(ICallBack callBack) {
    setHeader(request);
    okHttpClient.newCall(request.build()).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        callBack.onFailure(call, e.getMessage());
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        assert response.body() != null;
        callBack.onSuccessful(call, response.body().string());
      }
    });
  }

  /**
   * 为request添加请求头
   *
   * @param request
   */
  private void setHeader(Request.Builder request) {
    if (headerMap != null) {
      try {
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
          request.addHeader(entry.getKey(), entry.getValue());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 生成安全套接字工厂，用于https请求的证书跳过
   *
   * @return
   */
  private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
    SSLSocketFactory ssfFactory = null;
    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new SecureRandom());
      ssfFactory = sc.getSocketFactory();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ssfFactory;
  }

  private static TrustManager[] buildTrustManagers() {
    return new TrustManager[] { new X509TrustManager() {
      @Override
      public void checkClientTrusted(X509Certificate[] chain, String authType) {
      }

      @Override
      public void checkServerTrusted(X509Certificate[] chain, String authType) {
      }

      @Override
      public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[] {};
      }
    } };
  }

  /**
   * 自定义一个接口回调
   */
  public interface ICallBack {

    void onSuccessful(Call call, String data);

    void onFailure(Call call, String errorMsg);

  }
}
