/***
 * @Author: 码上talk|RC
 * @Date: 2021-04-30 14:13:26
 * @LastEditTime: 2021-10-06 14:58:26
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/json/ResponseJson.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.json;

import store.tacomall.common.enumeration.BizEnum;

import lombok.Data;

@Data
public class ResponseJson<T> {

  private Boolean status = false;

  private Integer code = BizEnum.ERROR.getCode();

  private String message = BizEnum.ERROR.getMessage();

  private T data;

  public void ok() {
    this.status = true;
    this.code = BizEnum.OK.getCode();
    this.message = BizEnum.OK.getMessage();
  }

}