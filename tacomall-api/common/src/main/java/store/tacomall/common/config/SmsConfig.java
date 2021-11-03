
/***
 * @Author: 码上talk|RC
 * @Date: 2021-06-25 17:45:16
 * @LastEditTime: 2021-10-06 14:59:25
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/config/SmsConfig.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.config;

import com.aliyun.teaopenapi.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import store.tacomall.common.properties.SmsProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(SmsProperties.class)
public class SmsConfig {
  private volatile static com.aliyun.dysmsapi20170525.Client client;

  public static String endpoint;

  public static String accessKey;

  public static String accessSecret;

  @Autowired
  public SmsConfig(SmsProperties properties) {
    endpoint = properties.getEndPoint();
    accessKey = properties.getAccesskey();
    accessSecret = properties.getAccessSecret();
  }

  public static com.aliyun.dysmsapi20170525.Client getSmsClient() {
    if (client == null) {
      synchronized (SmsConfig.class) {
        if (client == null) {
          try {
            Config config = new Config().setAccessKeyId(accessKey).setAccessKeySecret(accessSecret);
            config.endpoint = endpoint;
            client = new com.aliyun.dysmsapi20170525.Client(config);
          } catch (Exception e) {

          }
        }
      }
    }
    return client;
  }
}