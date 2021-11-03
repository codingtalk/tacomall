/***
 * @Author: 码上talk|RC
 * @Date: 2021-04-30 14:13:40
 * @LastEditTime: 2021-10-06 14:58:28
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/json/ResponsePageJson.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.json;

import java.util.HashMap;
import java.util.Map;

import store.tacomall.common.enumeration.BizEnum;

import lombok.Data;

@Data
public class ResponsePageJson<T> {

  private Boolean status = false;

  private Integer code = BizEnum.ERROR.getCode();

  private String message = BizEnum.ERROR.getMessage();

  private T data;

  private Map<String, Object> page = new HashMap<>();

  public void ok() {
    this.status = true;
    this.code = BizEnum.OK.getCode();
    this.message = BizEnum.OK.getMessage();
  }

  public void buildPage(long pageIndex, long pageSize, long total) {
    page.put("pageIndex", pageIndex);
    page.put("pageSize", pageSize);
    page.put("total", total);
  }

}
