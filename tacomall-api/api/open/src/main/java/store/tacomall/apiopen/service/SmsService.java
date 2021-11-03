/***
 * @Author: 码上talk|RC
 * @Date: 2021-08-02 14:32:55
 * @LastEditTime: 2021-10-06 14:18:17
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/service/SmsService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiopen.service;

import store.tacomall.common.json.ResponseJson;

public interface SmsService {

  ResponseJson<String> send(String app, Integer bizType, String mobile) throws Exception;
}
