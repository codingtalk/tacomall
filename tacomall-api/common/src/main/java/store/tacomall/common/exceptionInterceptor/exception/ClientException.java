/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-06 14:58:41
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/exceptionInterceptor/exception/ClientException.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.exceptionInterceptor.exception;

public class ClientException extends RuntimeException {
  public ClientException(String message) {
    super(message);
  }
}
