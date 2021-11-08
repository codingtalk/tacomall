/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-08 14:30:37
 * @LastEditTime: 2021-11-08 15:06:22
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/vo/page/PageCenterVo.java
 */
package store.tacomall.apima.vo.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageCenterVo {
  private Integer SumOrderUnPay;
  private Integer SumOrderPayed;
  private Integer SumOrderFinished;
  private Integer SumOrderReturn;
}
