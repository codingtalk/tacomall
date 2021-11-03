/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-11 22:56:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/config/OssConfig.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import store.tacomall.common.properties.OssProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfig {
  private volatile static OSSClient client;

  public static String endpoint;

  public static String accessKey;

  public static String accessSecret;

  public static String bucket;

  public static Long expire;

  public static String host;

  public static String callback;

  @Autowired
  public OssConfig(OssProperties properties) {
    endpoint = properties.getEndPoint();
    accessKey = properties.getAccesskey();
    accessSecret = properties.getAccessSecret();
    bucket = properties.getBucket();
    expire = properties.getExpire();
    host = properties.getHost();
    callback = properties.getCallback();
  }

  public static OSSClient getOSSClient() {
    if (client == null) {
      synchronized (OssConfig.class) {
        if (client == null) {
          client = new OSSClient(endpoint, accessKey, accessSecret);
        }
      }
    }
    return client;
  }
}
