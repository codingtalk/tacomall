/***
 * @Author: 码上talk|RC
 * @Date: 2021-09-18 14:01:11
 * @LastEditTime: 2021-10-11 22:54:20
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/properties/SmsProperties.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
/***
 * @Author: 码上talk|RC
 * @Date: 2021-06-25 17:47:48
 * @LastEditTime: 2021-08-02 14:46:13
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/cn/tacomall/common/properties/SmsProperties.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ali.sms")
public class SmsProperties {

  private String endPoint;

  private String accesskey;

  private String accessSecret;

}
