/***
 * @Author: 码上talk|RC
 * @Date: 2021-09-18 14:01:11
 * @LastEditTime: 2021-10-06 14:57:37
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/util/PasswordUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {

  public static String encode(String passwprd) {
    return DigestUtils.sha256Hex(passwprd);
  }
}
