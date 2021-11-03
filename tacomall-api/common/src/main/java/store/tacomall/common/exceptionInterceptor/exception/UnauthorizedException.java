/***
 * @Author: 码上talk|RC
 * @Date: 2021-09-18 14:01:09
 * @LastEditTime: 2021-10-06 14:58:53
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/exceptionInterceptor/exception/UnauthorizedException.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.exceptionInterceptor.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }
}
